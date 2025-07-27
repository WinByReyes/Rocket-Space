package controller;

import core.PlayerStats;

public class StatsController {
    private PlayerStats stats;

    public StatsController(PlayerStats stats) {
        this.stats = stats;
    }

    public void playerHit() {
        stats.loseLife();
    }

    public void collectScore(int value) {
        stats.addScore(value);
    }

    public void gainLife() {
        stats.addLife();
    }

    public void resetStats() {
        stats.reset();
    }

    public int getCurrentLives() {
        return stats.getLives();
    }

    public int getCurrentScore() {
        return stats.getScore();
    }
}