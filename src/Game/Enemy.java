package Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy{
    public int x, y;
    public int width = 60;
    public int height = 60;
    public int speed;

    private Image sprite;

    public Enemy(int panelWidth) {
        sprite = new ImageIcon("Assets/enemy.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        Random rand = new Random();

        int maxX = panelWidth - width;
        if (maxX < 1) maxX = 1;

        x = rand.nextInt(maxX);
        y = -height;

        speed = 2 + rand.nextInt(4);
    }

    public void update() {
        y += speed;
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }

    public Rectangle getArea() {
        return new Rectangle(x, y, width, height);
    }
}
