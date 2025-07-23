package MainMenu;
import game.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {
    public MenuPanel(GameController launcher) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);

        JButton startButton = new JButton("Iniciar Juego");
        JButton exitButton = new JButton("Salir");
        Color colorStartButton = new Color(144, 238, 178);
        Color colorExitButton = new Color(	255, 166, 125);

        startButton.setBackground(colorStartButton);
        exitButton.setBackground(colorExitButton);

        startButton.setBorderPainted(false);
        exitButton.setBorderPainted(false);

        startButton.setAlignmentX(Component.BOTTOM_ALIGNMENT);
        exitButton.setAlignmentX(Component.BOTTOM_ALIGNMENT);

        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));

        startButton.addActionListener((ActionEvent e) -> {
            launcher.mostrarGamePanel(); // Llama al método que cambia de panel
        });

        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        add(Box.createVerticalGlue());
        add(startButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(exitButton);
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    try {
        BufferedImage bg = ImageIO.read(new File("Sprites/BackGround.png"));
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
    } catch (IOException e) {
        g.setColor(Color.DARK_GRAY); // Fallback color
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

}
