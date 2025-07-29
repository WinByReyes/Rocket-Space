package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Disparo {
    private int x, y;
    private int velocidad = 10;
    private BufferedImage imagen;
    private int ancho = 10;
    private int alto = 20;
    private boolean activo = true;

    public Disparo(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            imagen = ImageIO.read(new File("Sprites/Bullet.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen del disparo.");
        }
    }

    public void actualizar() {
        y -= velocidad;
        if (y + alto < 0) {
            activo = false;
        }
    }

    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, ancho, alto);
        }
    }

    public boolean estaActivo() {
        return activo;
    }

    public void destruir() {
        activo = false;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
