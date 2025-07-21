package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private Nave nave;
    private Timer timer;
    private int panelWidth;
    private int panelHeight;

    public GamePanel(int width, int height) {
        this.panelWidth = width;
        this.panelHeight = height;

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
