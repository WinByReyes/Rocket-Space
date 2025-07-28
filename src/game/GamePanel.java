package game;

import Interfaz.KSPanelInfo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private Nave nave;
    private Timer timer;
    private int panelWidth;
    private int panelHeight;

    private int puntos = 0;
    private int vidas = 3;
    private int maxPuntos = 0;

    private KSPanelInfo panelInfo;

    public GamePanel(int width, int height, KSPanelInfo panelInfo) {
        this.panelWidth = width;
        this.panelHeight = height;
        this.panelInfo = panelInfo;

        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        nave = new Nave(panelWidth / 2 - 20, panelHeight - 80, panelWidth, panelHeight);
        timer = new Timer(16, this); // ~60 FPS
    }

    public void startGame() {
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        nave.dibujar(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nave.actualizar();
        repaint();
    }

    // Métodos públicos para que otros componentes (por ejemplo, meteoritos) puedan llamar

    public void sumarPuntos(int cantidad) {
        puntos += cantidad;
        if (puntos > maxPuntos) maxPuntos = puntos;
        actualizarInterfaz();
    }

    public void perderVida() {
        vidas--;
        if (vidas <= 0) {
            vidas = 0;
            gameOver();
        }
        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        panelInfo.setPuntos(puntos);
        panelInfo.setVidas(vidas);
        panelInfo.setMaxPuntos(maxPuntos);
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "¡Juego terminado!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        // Aquí puedes agregar un menú o reiniciar el juego si quieres
    }

    @Override
    public void keyPressed(KeyEvent e) {
        nave.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        nave.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
