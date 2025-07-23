package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private Nave nave;
    private Timer timer;
    private int panelWidth;
    private int panelHeight;
    private BufferedImage fondo;
    private int fondoY = 0;
    private int VelocityFondo = 1;

    public GamePanel(int width, int height) {
        this.panelWidth = width;
        this.panelHeight = height;

        

        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        nave = new Nave(panelWidth / 2 - 20, panelHeight - 80, panelWidth, panelHeight);
        timer = new Timer(16, this); // ~60 FPS
        try {
            fondo = ImageIO.read(new File("Sprites/BackgrounSpace.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar el fondo");
            e.printStackTrace();
        }
    }

    public void startGame() {
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar fondo en scroll
        if (fondo != null) {
            g.drawImage(fondo, 0, fondoY, getWidth(), fondo.getHeight(), null);
            g.drawImage(fondo, 0, fondoY - fondo.getHeight(), getWidth(), fondo.getHeight(), null);
        }

        // Dibujar la nave encima del fondo
        nave.dibujar(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fondoY += VelocityFondo;
    if (fondoY >= fondo.getHeight() / 2) {
        fondoY = 0;
}
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
