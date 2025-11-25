package Game;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        setTitle("Aero Fighters");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        GamePanel panel = new GamePanel();
        add(panel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
