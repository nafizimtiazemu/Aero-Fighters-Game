package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {

    private Timer timer;
    Player player;

    public static final int WIDTH = 480;
    public static final int HEIGHT = 720;
    public boolean left, right, up, down;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        setFocusable(true);
        requestFocusInWindow();

        timer = new Timer(16, this);
        timer.start();

        player = new Player(WIDTH/2-20, HEIGHT - 100);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) left = true;
                if (key == KeyEvent.VK_RIGHT) right = true;
                if (key == KeyEvent.VK_UP) up = true;
                if (key == KeyEvent.VK_DOWN) down = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) left = false;
                if (key == KeyEvent.VK_RIGHT) right = false;
                if (key == KeyEvent.VK_UP) up = false;
                if (key == KeyEvent.VK_DOWN) down = false;
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (left) player.moveLeft();
        if (right) player.moveRight();
        if (up) player.moveUp();
        if (down) player.moveDown();

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.drawString("Aero Fighters Base Running", 140, 100);

        player.draw(g);
    }
}
