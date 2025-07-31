package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import util.SoundPlayer;

public class Nave {
    private int x, y;
    private int velocidad = 10;
    private int ancho = 40;
    private int alto = 40;
    private boolean izquierda, derecha, arriba, abajo;
    private int limiteAncho;
    private int limiteAlto;
    private BufferedImage sprite;
    private long tiempoUltimoDisparo = 0;
    private final int cooldownDisparo = 300; // milisegundos entre disparos (0.3 segundos
    private SoundPlayer disparoSonido = new SoundPlayer("disparo.wav", false);

    private List<Disparo> disparos = new ArrayList<>();

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

        // Actualizar disparos
        disparos.forEach(Disparo::actualizar);
        disparos.removeIf(d -> !d.estaActivo());
    }

    public void dibujar(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.CYAN);
            g.fillRect(x, y, ancho, alto);
        }

        // Dibujar disparos
        for (Disparo d : disparos) {
            d.dibujar(g);
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_A -> izquierda = true;
        case KeyEvent.VK_D -> derecha = true;
        case KeyEvent.VK_W -> arriba = true;
        case KeyEvent.VK_S -> abajo = true;
        case KeyEvent.VK_SPACE -> disparar(); // Separamos el disparo
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

    private void disparar() {
        long ahora = System.currentTimeMillis();
        disparoSonido.playOnce();
    if (ahora - tiempoUltimoDisparo >= cooldownDisparo) {
        tiempoUltimoDisparo = ahora;

        int centroX = x + ancho / 2 - 5;
        int parteSuperiorY = y;
        disparos.add(new Disparo(centroX, parteSuperiorY));
    }
    }

    public List<Disparo> getDisparos() {
        return disparos;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    public void reset() {
    x = limiteAncho / 2 - ancho / 2;
    y = limiteAlto - 80;
    izquierda = derecha = arriba = abajo = false;
}
public void setIzquierda(boolean estado) { izquierda = estado; }
public void setDerecha(boolean estado) { derecha = estado; }
public void setArriba(boolean estado) { arriba = estado; }
public void setAbajo(boolean estado) { abajo = estado; }

public void dispararDesdeMando() {
    long ahora = System.currentTimeMillis();
    if (ahora - tiempoUltimoDisparo >= cooldownDisparo) {
        tiempoUltimoDisparo = ahora;
        disparos.add(new Disparo(x + ancho / 2 - 5, y));
    }
}

}
