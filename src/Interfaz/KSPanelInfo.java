package Interfaz;

import javax.swing.*;
import java.awt.*;

public class KSPanelInfo extends JPanel {
    private JLabel lblPuntos;
    private JLabel lblVidas;
    private JLabel lblMaxPuntos;

    public KSPanelInfo() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        lblVidas = new JLabel("Vidas: 3");
        lblPuntos = new JLabel("Puntos: 0");
        lblMaxPuntos = new JLabel("Máximo: 0");

        Font font = new Font("Arial", Font.BOLD, 18);
        lblVidas.setFont(font);
        lblPuntos.setFont(font);
        lblMaxPuntos.setFont(font);

        lblVidas.setForeground(Color.RED);
        lblPuntos.setForeground(Color.GREEN);
        lblMaxPuntos.setForeground(Color.YELLOW);

        add(lblVidas, BorderLayout.WEST);

        // Panel derecho que contiene puntos y máximo, con FlowLayout a la derecha
        JPanel panelDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelDerecho.setBackground(Color.BLACK);
        panelDerecho.add(lblPuntos);
        panelDerecho.add(lblMaxPuntos);

        add(panelDerecho, BorderLayout.EAST);

        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    public void setPuntos(int puntos) {
        lblPuntos.setText("Puntos: " + puntos);
    }

    public void setVidas(int vidas) {
        lblVidas.setText("Vidas: " + vidas);
    }

    public void setMaxPuntos(int maxPuntos) {
        lblMaxPuntos.setText("Máximo: " + maxPuntos);
    }
}
