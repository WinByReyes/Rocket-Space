package core;

public class PlayerStatsImpl implements PlayerStats {
    private int lives;
    private int score;

    public PlayerStatsImpl(int initialLives) {
        this.lives = initialLives;
        this.score = 0;
    }

    public int getLives() { return lives; }
    public void loseLife() { if (lives > 0) lives--; }
    public void addLife() { lives++; }
    public int getScore() { return score; }
    public void addScore(int value) { score += value; }
    public void reset() {
        lives = 3;
        score = 0;
    }
}