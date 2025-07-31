package util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private Clip clip;

    public SoundPlayer(String rutaRelativa, boolean loop) {
        try {
            File archivo = new File("Sprites/sounds/" + rutaRelativa);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();
            clip.open(audioInput);

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error al cargar sonido: " + rutaRelativa);
            e.printStackTrace();
        }
    }

    public void playOnce() {
        if (clip != null) {
            if (clip.isRunning()) clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void stop() {
        if (clip != null) clip.stop();
    }
}
