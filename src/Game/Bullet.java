package Game;

import javax.swing.*;
import java.awt.*;

public class Bullet {

    public int x, y;
    public int width = 20;
    public int height = 35;
    public int speed = 10;

    private Image sprite;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;

        sprite = new ImageIcon("Assets/bullet.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public void update() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }

    public Rectangle getArea() {
        return new Rectangle(x, y, width, height);
    }

}
