package game;

import javax.swing.*;

import InterfazVida.KSPanelInfo;

import java.awt.*;
import input.*;
import MainMenu.CreditosPanel;
import MainMenu.MenuPanel;
import MainMenu.RankingPanel;

public class GameController {
    private JFrame window;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private KSPanelInfo panelInfo;
    private JPanel gameContainer;

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    public static GameController instance; // al inicio de tu clase
    private GamepadManager gamepadManager = new GamepadManager();

    public void start() {
        window = new JFrame("Galactic Shield");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        window.setResizable(false);

        // Crear el HUD (panel superior)
        panelInfo = new KSPanelInfo();

        // Crear el panel del juego
        this.gamePanel = new GamePanel(WIDTH, HEIGHT - 100, panelInfo);

        // Crear contenedor para juego + HUD
        gameContainer = new JPanel(new BorderLayout());
        gameContainer.add(panelInfo, BorderLayout.NORTH);
        gameContainer.add(gamePanel, BorderLayout.CENTER);

        // Menú principal
        menuPanel = new MenuPanel(this);
        window.setContentPane(menuPanel);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void mostrarGamePanel() {
        window.setContentPane(gameContainer);
        window.revalidate();
        window.repaint();
        gamePanel.requestFocusInWindow();
        gamePanel.startGame();
    }

    public GameController() {
    instance = this;

    Timer menuTimer = new Timer(16, e -> {
    gamepadManager.actualizarMenu(menuPanel);
});
menuTimer.start();
}

public static void volverAlMenuStatic() {
    if (instance != null) {
        instance.window.setContentPane(instance.menuPanel);
        instance.window.revalidate();
        instance.window.repaint();
    }
}
public void mostrarMenuPrincipal() {
    window.setContentPane(menuPanel);
    window.revalidate();
    window.repaint();
    menuPanel.requestFocusInWindow();
}

public void mostrarRanking() {
    RankingPanel rankingPanel = new RankingPanel(this);
    window.setContentPane(rankingPanel);
    window.revalidate();
    window.repaint();
    rankingPanel.requestFocusInWindow();
}

public void mostrarCreditos() {
    CreditosPanel creditosPanel = new CreditosPanel(this);
    window.setContentPane(creditosPanel);
    window.revalidate();
    window.repaint();
    creditosPanel.requestFocusInWindow();
}

}

