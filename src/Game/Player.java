package Game;

import javax.swing.*;
import java.awt.*;

public class Player {

    public int x, y;
    public int width = 60;
    public int height = 60;
    public int speed = 6;

    private Image sprite;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        sprite = new ImageIcon("Assets/player.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public void moveLeft() { x -= speed; }
    public void moveRight() { x += speed; }
    public void moveUp() { y -= speed; }
    public void moveDown() { y += speed; }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }

    public Rectangle getArea() {
        return new Rectangle(x, y, width, height);
    }
}
