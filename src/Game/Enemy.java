package Game;

import java.awt.*;
import java.util.Random;

public class Enemy{
    public int x, y;
    public int width = 40;
    public int height = 40;
    public int speed;

    public Enemy(int panelWidth) {
        Random rand = new Random();

        this.x = rand.nextInt(panelWidth - width);

        this.y = -height;

        this.speed = 2 + rand.nextInt(4);
    }

    public void update() {
        y += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getArea() {
        return new Rectangle(x, y, width, height);
    }
}
