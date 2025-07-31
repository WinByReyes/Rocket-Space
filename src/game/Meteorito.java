package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Meteorito {
    private int x, y;
    private int ancho = 50;
    private int alto = 50;
    private int velocidad;
    private BufferedImage imagen;
    private boolean activo = true;

    private int limiteAlto;

    private static Random random = new Random();

    public Meteorito(int limiteAncho, int limiteAlto) {
        this.limiteAlto = limiteAlto;

        // Posición X aleatoria dentro del ancho de la ventana
        this.x = random.nextInt(limiteAncho - ancho);

        // Aparecen arriba (fuera de la pantalla)
        this.y = -alto;

        // Velocidad aleatoria entre 3 y 7(suma 1 al bound) píxeles por frame (ajustable)
        this.velocidad = 3 + random.nextInt(6);

        try {
            imagen = ImageIO.read(new File("Sprites/Meteorito.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen del meteorito. Se usará una forma básica.");
        }
    }

    public void actualizar() {
        if (!activo) return;

        y += velocidad;

        // Si pasa el límite inferior, lo desactivamos (sale de pantalla)
        if (y > limiteAlto) {
            activo = false;
        }
    }

    public void dibujar(Graphics g) {
        if (!activo) return;

        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.GRAY);
            g.fillOval(x, y, ancho, alto);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    public boolean estaActivo() {
        return activo;
    }

    public void destruir() {
        activo = false;
    }
}
