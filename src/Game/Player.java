package Game;

import java.awt.*;

public class Player {

    public int x, y;
    public int width = 40;
    public int height = 40;
    public int speed = 6;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void moveLeft() { x -= speed; }
    public void moveRight() { x += speed; }
    public void moveUp() { y -= speed; }
    public void moveDown() { y += speed; }

    public void draw(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, width, height);
    }
}
