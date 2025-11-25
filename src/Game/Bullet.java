package Game;

import java.awt.*;

public class Bullet {

    public int x, y;
    public int width = 5;
    public int height = 12;
    public int speed = 10;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getArea() {
        return new Rectangle(x, y, width, height);
    }

}
