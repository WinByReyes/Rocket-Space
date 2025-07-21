package game;

import javax.swing.*;

public class GameController {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final String TITLE = "Navecita Espacial";

    public void start() {
        JFrame window = new JFrame(TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel panel = new GamePanel(WIDTH, HEIGHT);
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.startGame(); // Inicia el juego (timer y lógica)
    }
}
