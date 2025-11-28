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
    ArrayList<PowerUp> powerups = new ArrayList<>();

    public static final int WIDTH = 480;
    public static final int HEIGHT = 720;
    public boolean left, right, up, down;

    boolean shooting = false;
    int shootCooldown = 0;

    int waveTimer = 0;
    int difficulty = 1;

    int rapidFireTimer = 0;
    int shieldTimer = 0;

    int playerHP = 3;

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

        if (rapidFireTimer > 0) rapidFireTimer--;
        if (shieldTimer > 0) shieldTimer--;

        int currentCooldown = (rapidFireTimer > 0) ? 4 : 10;

        if (shooting && shootCooldown == 0) {
            bullets.add(new Bullet(player.x + player.width / 2 - 2, player.y));
            shootCooldown = currentCooldown;
        }

        if (shootCooldown > 0) shootCooldown--;

        for(int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.update();

            if (b.y < -20) {
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
                    i--;

                    if (enemy.hp <= 0) {

                        // 10% chance to drop a powerup
                        if (rand.nextInt(100) < 10) {
                            powerups.add(new PowerUp(enemy.x, enemy.y));
                        }

                        enemies.remove(j);
                    }
                    break;
                }
            }
        }

        for (int i = 0; i < powerups.size(); i++) {
            PowerUp p = powerups.get(i);
            p.update();

            // Remove off-screen
            if (p.y > HEIGHT) {
                powerups.remove(i);
                i--;
            }
        }

        for (int i = 0; i < powerups.size(); i++) {
            PowerUp p = powerups.get(i);

            if (player.getArea().intersects(p.getArea())) {

                // Apply effect
                if (p.type == 0) {  // Health
                    playerHP++;
                    System.out.println("Health +1");
                }
                if (p.type == 1) {  // Rapid Fire
                    rapidFireTimer = 600;  // 10 seconds
                    System.out.println("Rapid Fire Activated!");
                }
                if (p.type == 2) {  // Shield
                    shieldTimer = 600;
                    System.out.println("Shield Activated!");
                }

                // Remove collected power-up
                powerups.remove(i);
                i--;
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        background.draw(g);

        player.draw(g);

        for (Bullet b: bullets) b.draw(g);

        for (Enemy e: enemies) e.draw(g);

        for (PowerUp p : powerups) p.draw(g);
    }
}
