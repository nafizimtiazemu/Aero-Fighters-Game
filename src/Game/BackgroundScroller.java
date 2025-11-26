package Game;

import javax.swing.*;
import java.awt.*;

public class BackgroundScroller {

    private Image image;   // Background image
    private int y1;        // First image Y position
    private int y2;        // Second image Y position
    private int speed = 2; // Scrolling speed

    private int width;     // Background width
    private int height;    // Background height

    public BackgroundScroller() {

        image = new ImageIcon("Assets/background.png").getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        // Initial positions
        y1 = 0;
        y2 = -height;
    }

    public void update() {
        y1 += speed;
        y2 += speed;

        // Reset when they move below screen
        if (y1 >= height) {
            y1 = y2 - height;
        }
        if (y2 >= height) {
            y2 = y1 - height;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, 0, y1, null);
        g.drawImage(image, 0, y2, null);
    }
}
