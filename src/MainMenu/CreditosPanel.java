package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class CreditosPanel extends JPanel implements KeyListener, ActionListener {
    private game.GameController controller;
    private BufferedImage fondo;
    private Timer timer;
    private int offsetY = 600;  // Posición inicial desde donde comienzan los créditos
    private final int velocidadScroll = 1;

    private final String[] lineas = {
        "Galactic Shield 🚀",
        " ",
        "👨‍💻 Programadores:",
        "Darwin Reyes",
        "Esteban Quinapanta",
        "Dilan Rodríguez",
        "Alfredo Romero",
        "Manuel Sares",
        "Karen Suárez",
        " ",
        "🎨 Gráficos:",
        "Nave sprite: Dragoon Domain",
        "Bala: Pixilart",
        " ",
        "🛠️ Herramientas:",
        "Java + Swing",
        "JInput (control Bluetooth)",
        "Visual Studio Code",
        "GitHub",
        " ",
        "🙏 Agradecimientos:",
        "A nuestros profes y compañeros",
        "A todos los que probaron el juego",
        "¡Y a ti por jugar! ❤️",
        " ",
        "© 2025"
    };

    public CreditosPanel(game.GameController controller) {
        this.controller = controller;
        setFocusable(true);
        addKeyListener(this);

        try {
            fondo = ImageIO.read(new File("Sprites/BackgroundCrRa.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar el fondo de créditos.");
        }

        timer = new Timer(30, this); // actualiza cada 30ms (~33fps)
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        String titulo = "Créditos";
        int ancho = g.getFontMetrics().stringWidth(titulo);
        g2.drawString(titulo, (getWidth() - ancho) / 2, 80);

        g2.setFont(new Font("Serif", Font.PLAIN, 22));
        int y = offsetY;

        for (String linea : lineas) {
            int lw = g.getFontMetrics().stringWidth(linea);
            g2.drawString(linea, (getWidth() - lw) / 2, y);
            y += 35;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        offsetY -= velocidadScroll;

        // Si los créditos se han desplazado completamente, se reinician o vuelven al menú
        if (offsetY + lineas.length * 35 < 0) {
            controller.mostrarMenuPrincipal();
            timer.stop();
        }

        repaint();
    }

    @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            timer.stop();
            controller.mostrarMenuPrincipal();
        }
    }
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
