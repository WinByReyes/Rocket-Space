package main;

import core.PlayerStatsImpl;
import controller.StatsController;
import ui.HUD;

import java.awt.Graphics;
import javax.swing.JPanel;

public class GameScreen extends JPanel {
    private StatsController controller;
    private HUD hud;

    public GameScreen() {
        PlayerStatsImpl stats = new PlayerStatsImpl(3);
        this.controller = new StatsController(stats);
        this.hud = new HUD(stats);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        hud.draw(g);
    }

    public void simulateGameplay() {
        controller.collectScore(100);
        controller.playerHit();
        repaint();
    }
}