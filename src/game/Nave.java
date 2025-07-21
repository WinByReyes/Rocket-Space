package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Nave {
    private int x, y;
    private int velocidad = 5;
    private int ancho = 40;
    private int alto = 40;
    private boolean izquierda, derecha, arriba, abajo;
    private int limiteAncho;
    private int limiteAlto;
    private BufferedImage sprite;

    public Nave(int x, int y, int limiteAncho, int limiteAlto) {
        this.x = x;
        this.y = y;
        this.limiteAncho = limiteAncho;
        this.limiteAlto = limiteAlto;
        try {
            sprite = ImageIO.read(new File("Sprites/ShipSprite.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen de la nave. Se usará una forma básica.");
        }
    }

    public void actualizar() {
        if (izquierda && x > 0) x -= velocidad;
        if (derecha && x + ancho < limiteAncho) x += velocidad;
        if (arriba && y > 0) y -= velocidad;
        if (abajo && y + alto < limiteAlto) y += velocidad;
    }

    public void dibujar(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.CYAN);
            g.fillRect(x, y, ancho, alto);
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> izquierda = true;
            case KeyEvent.VK_D -> derecha = true;
            case KeyEvent.VK_W -> arriba = true;
            case KeyEvent.VK_S -> abajo = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> izquierda = false;
            case KeyEvent.VK_D -> derecha = false;
            case KeyEvent.VK_W -> arriba = false;
            case KeyEvent.VK_S -> abajo = false;
        }
    }
}
