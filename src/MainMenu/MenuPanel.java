package MainMenu;

import javax.swing.*;

import database.RankingDB;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuPanel extends JPanel {
    private List<JButton> botones = new ArrayList<>();
    private int seleccionado = 0;
    private BufferedImage fondo;

    public MenuPanel(game.GameController controller) {
        setLayout(null); // Usamos posicionamiento manual
        setFocusable(true);

        try {
            fondo = ImageIO.read(new File("Sprites/MenuBackGround.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen del fondo del menú.");
        }

        // Crear botones
        JButton jugarBtn = crearBoton("Jugar", () -> controller.mostrarGamePanel());
        JButton rankingBtn = crearBoton("Ranking", () -> controller.mostrarRanking());
        JButton creditosBtn = crearBoton("Créditos", () -> controller.mostrarCreditos());

        JButton salirBtn = crearBoton("Salir", () -> System.exit(0));

        botones.add(jugarBtn);
        botones.add(rankingBtn);
        botones.add(creditosBtn);
        botones.add(salirBtn);

        // Posicionar botones en la parte izquierda
        int x = 60;
        int y = 100;
        int width = 180;
        int height = 40;
        int separacion = 20;

        for (JButton b : botones) {
            b.setBounds(x, y, width, height);
            add(b);
            y += height + separacion;
        }

        actualizarSeleccionVisual();
    }

    private JButton crearBoton(String texto, Runnable accion) {
        JButton btn = new JButton(texto);
        btn.setFocusable(false);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.BLUE);
        btn.addActionListener(e -> accion.run());
        return btn;
    }

    private void actualizarSeleccionVisual() {
        for (int i = 0; i < botones.size(); i++) {
            botones.get(i).setBackground(i == seleccionado ? Color.yellow : Color.DARK_GRAY);
        }
    }

    public void moverSeleccion(int direccion) {
        seleccionado += direccion;
        if (seleccionado < 0) seleccionado = botones.size() - 1;
        if (seleccionado >= botones.size()) seleccionado = 0;
        actualizarSeleccionVisual();
    }

    public void activarSeleccion() {
        botones.get(seleccionado).doClick();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
