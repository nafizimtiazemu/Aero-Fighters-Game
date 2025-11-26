package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    private Timer timer;
    Player player;

    BackgroundScroller background;

    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();

    public static final int WIDTH = 480;
    public static final int HEIGHT = 720;
    public boolean left, right, up, down;

    boolean shooting = false;
    int shootCooldown = 0;

    int waveTimer = 0;
    int difficulty = 1;

    Random rand = new Random();

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        setFocusable(true);
        requestFocusInWindow();

        background = new BackgroundScroller();

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

                if (key == KeyEvent.VK_SPACE) shooting = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) left = false;
                if (key == KeyEvent.VK_RIGHT) right = false;
                if (key == KeyEvent.VK_UP) up = false;
                if (key == KeyEvent.VK_DOWN) down = false;

                if (key == KeyEvent.VK_SPACE) shooting = false;
            }
        });

        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        background.update();

        if (left) player.moveLeft();
        if (right) player.moveRight();
        if (up) player.moveUp();
        if (down) player.moveDown();

        if (shooting && shootCooldown == 0) {
            bullets.add(new Bullet(player.x, player.y));
            shootCooldown = 10;
        }

        if (shootCooldown > 0) {
            shootCooldown--;
        }

        for(int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.update();

            if (b.y < -10) {
                bullets.remove(i);
                i--;
            }
        }

        waveTimer++;

        if (waveTimer % 600 == 0) {
            difficulty++;
            System.out.println("Difficulty: " + difficulty);
        }

        if (rand.nextInt(100) < difficulty * 2) {
            enemies.add(new Enemy(WIDTH));
        }

        for(int i =0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.update();

            if (enemy.y > HEIGHT) {
                enemies.remove(i);
                i--;
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);

            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);

                if (b.getArea().intersects(enemy.getArea())) {
                    enemy.hp--;

                    bullets.remove(i);

                    if (enemy.hp <= 0) {
                        enemies.remove(j);
                    }
                    i--;
                    break;
                }
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        background.draw(g);

        player.draw(g);

        for (Bullet b: bullets) {
            b.draw(g);
        }

        for (Enemy e: enemies) {
            e.draw(g);
        }
    }
}
