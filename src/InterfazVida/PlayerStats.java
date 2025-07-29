package InterfazVida;

public interface PlayerStats {
    int getLives();
    void loseLife();
    void addLife();
    int getScore();
    void addScore(int value);
    void reset();
}