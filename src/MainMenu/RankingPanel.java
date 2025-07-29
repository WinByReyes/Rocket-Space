package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import database.RankingDB;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class RankingPanel extends JPanel implements KeyListener {
    private game.GameController controller;
    private BufferedImage fondo;
    private List<String> top;

    public RankingPanel(game.GameController controller) {
        this.controller = controller;
        setFocusable(true);
        addKeyListener(this);

        // Cargar fondo
        try {
            fondo = ImageIO.read(new File("Sprites/BackgroundCrRa.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar el fondo del ranking.");
        }

        // Obtener ranking
        RankingDB db = new RankingDB();
        top = db.obtenerRanking();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fondo
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);

        // Título centrado
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        String titulo = "Ranking";
        int anchoTitulo = g.getFontMetrics().stringWidth(titulo);
        g2.drawString(titulo, (getWidth() - anchoTitulo) / 2, 80);

        // Lista centrada
        g2.setFont(new Font("Consolas", Font.PLAIN, 22));
        int y = 140;
        int pos = 1;

        if (top.isEmpty()) {
            String texto = "Aún no hay puntajes.";
            int w = g.getFontMetrics().stringWidth(texto);
            g2.drawString(texto, (getWidth() - w) / 2, y);
        } else {
            for (String entrada : top) {
                String texto = pos++ + ". " + entrada;
                int w = g.getFontMetrics().stringWidth(texto);
                g2.drawString(texto, (getWidth() - w) / 2, y);
                y += 40;
                if (pos > 6) break;
            }
        }
    }

    @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            controller.mostrarMenuPrincipal();
        }
    }
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
