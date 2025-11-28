package Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PowerUp {

    public int x, y;
    public int type;
    public int speed = 3;
    public int width = 32;
    public int height = 32;

    public Image sprite;

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;

        Random rand = new Random();
        type = rand.nextInt(3);

        String path = switch (type) {
            case 0 -> "Assets/power_health.png";
            case 1 -> "Assets/power_rapid.png";
            case 2 -> "Assets/power_shield.png";
            default -> "Assets/power_health.png";

        };

        sprite = new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
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


