package game;

import Interfaz.KSPanelInfo;
import javax.swing.*;
import java.awt.*;

public class GameController {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final String TITLE = "Juego Espacial - Karen Suárez";

    public void start() {
        JFrame window = new JFrame(TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        KSPanelInfo panelInfo = new KSPanelInfo();
        GamePanel gamePanel = new GamePanel(WIDTH, HEIGHT - 100, panelInfo);

        window.setLayout(new BorderLayout());
        window.add(panelInfo, BorderLayout.NORTH);
        window.add(gamePanel, BorderLayout.CENTER);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameController().start();
        });
    }
}
