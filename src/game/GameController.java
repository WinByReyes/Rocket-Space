package game;

import javax.swing.*;

import MainMenu.MenuPanel;

public class GameController{
    private JFrame window;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;

    public void start() {
        window = new JFrame("Rockect Shield");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        menuPanel = new MenuPanel(this);
        window.setContentPane(menuPanel);

        window.setVisible(true);
    }

    public void mostrarGamePanel() {
        gamePanel = new GamePanel(800, 600);
        window.setContentPane(gamePanel);
        window.revalidate(); // Actualiza el contenido
        gamePanel.requestFocusInWindow(); // Enfoca el panel para leer teclas
        gamePanel.startGame(); // Inicia el juego
    }
}
