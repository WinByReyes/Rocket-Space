package game;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import InterfazVida.KSPanelInfo;
import input.GamepadManager;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private Nave nave;
    private Timer timer;
    private int panelWidth;
    private int panelHeight;
    private BufferedImage fondo;
    private int fondoY = 0;
    private int VelocityFondo = 1;

    private int puntos = 0;
    private int vidas = 3;
    private int maxPuntos = 0;

    private KSPanelInfo panelInfo;

    private List<Meteorito> meteoritos = new ArrayList<>();
    private Random random = new Random();

    private int spawnCounter = 0;
    private int spawnInterval = 60; // cada 60 frames (aprox 1 segundo)

    private GamepadManager gamepadManager = new GamepadManager();

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

    // Fondo scroll
    if (fondo != null) {
        g.drawImage(fondo, 0, fondoY, getWidth(), fondo.getHeight(), null);
        g.drawImage(fondo, 0, fondoY - fondo.getHeight(), getWidth(), fondo.getHeight(), null);
    }

    // Dibujar meteoritos
    for (Meteorito m : meteoritos) {
        m.dibujar(g);
    }

    // Dibujar nave y disparos
    nave.dibujar(g);
}


   @Override
public void actionPerformed(ActionEvent e) {
    fondoY += VelocityFondo;
    if (fondoY >= fondo.getHeight() / 2) {
        fondoY = 0;
    }

    spawnCounter++;
    if (spawnCounter >= spawnInterval) {
        spawnCounter = 0;
        meteoritos.add(new Meteorito(panelWidth, panelHeight));
    }

    gamepadManager.actualizar(nave, this); // ✅ llamado correcto

    nave.actualizar();

    // Actualizar meteoritos
    Iterator<Meteorito> iterMete = meteoritos.iterator();
    while (iterMete.hasNext()) {
        Meteorito m = iterMete.next();
        m.actualizar();

        // Detectar colisión meteorito - nave
        if (m.estaActivo() && m.getBounds().intersects(nave.getBounds())) {
            perderVida();
            m.destruir();
        }

        // Remover meteoritos inactivos
        if (!m.estaActivo()) {
            iterMete.remove();
        }
    }

    // Detectar colisiones disparos - meteoritos
    Iterator<Disparo> iterDisparos = nave.getDisparos().iterator();
    while (iterDisparos.hasNext()) {
        Disparo d = iterDisparos.next();
        Iterator<Meteorito> iterM = meteoritos.iterator();
        boolean disparoDestruido = false;

        while (iterM.hasNext() && !disparoDestruido) {
            Meteorito m = iterM.next();
            if (m.estaActivo() && d.getBounds().intersects(m.getBounds())) {
                m.destruir();
                iterM.remove();

                disparoDestruido = true;
                iterDisparos.remove();

                sumarPuntos(10); // +10 puntos por destruir meteorito
            }
        }
    }

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

    String nombre = JOptionPane.showInputDialog(this, "Ingresa tu nombre para guardar el puntaje:");

    if (nombre != null && !nombre.trim().isEmpty()) {
        database.RankingDB db = new database.RankingDB();
        db.insertarPuntaje(nombre.trim(), puntos);
    }

    JOptionPane.showMessageDialog(this, "¡Juego terminado!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

    // Volver al menú principal
    SwingUtilities.invokeLater(() -> {
        Container parent = getTopLevelAncestor();
        if (parent instanceof JFrame frame) {
            game.GameController controller = new game.GameController();
            controller.start(); // Reinicia el menú principal
            frame.dispose(); // Cierra la ventana actual si es necesario
        }
    });
}



private void reiniciarJuego() {
    puntos = 0;
    vidas = 3;
    maxPuntos = 0;
    actualizarInterfaz();
    nave.reset(); // Debes crear este método en Nave
    timer.start();
}


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        pausarJuego();
        mostrarMenuPausa();
    } else {
        nave.keyPressed(e);
    }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        nave.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
    
    private void pausarJuego() {
    timer.stop();
}

private void reanudarJuego() {
    timer.start();
}

public void mostrarMenuPausa() {
    int opcion = JOptionPane.showOptionDialog(
        this,
        "Juego en pausa",
        "Pausa",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE,
        null,
        new String[] { "Continuar", "Reiniciar", "Salir al menú" },
        "Continuar"
    );

    if (opcion == 0) {
        reanudarJuego();
    } else if (opcion == 1) {
        reiniciarJuego();
    } else if (opcion == 2) {
        GameController.volverAlMenuStatic();
    }
}

}
