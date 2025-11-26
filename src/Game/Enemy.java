package Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy{
    public int x, y;
    public int width = 60;
    public int height = 60;
    public int speed;

    public int type;
    public int hp;
    public int dx = 0;

    private Image sprite;

    public Enemy(int panelWidth) {
        sprite = new ImageIcon("Assets/enemy.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        Random rand = new Random();

        type = rand.nextInt(3);

        int maxX = panelWidth - width;
        if (maxX < 1) maxX = 1;

        x = rand.nextInt(maxX);
        y = -height;

        speed = 2 + rand.nextInt(3);

        if (type == 0) hp = 1;
        if (type == 1) hp = 2;
        if (type == 2) hp = 3;

        if (type == 1 ) {
            dx = rand.nextBoolean() ? 3 : -3;
        }

        if (type == 2) {
            dx = rand.nextBoolean() ? 2 : -2;
        }

    }

    public void update() {
        y += speed;

        if (type == 1 ) {
            x += dx;

            if ( x < 0 || x > 480 - width) {
                dx = -dx;
            }
        }

        if ( type == 2) {
            x += dx;
        }

    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }

    public Rectangle getArea() {
        return new Rectangle(x, y, width, height);
    }
}
