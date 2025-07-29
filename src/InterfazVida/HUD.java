package InterfazVida;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class HUD {
    private PlayerStats stats;

    public HUD(PlayerStats stats) {
        this.stats = stats;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Lives: " + stats.getLives(), 20, 30);
        g.drawString("Score: " + stats.getScore(), 20, 60);
    }
}