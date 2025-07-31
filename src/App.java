import game.GameController;
import util.SoundPlayer;

public class App {
    public static void main(String[] args) {
        SoundPlayer fondo = new SoundPlayer("loading.wav", true); // Se repite
        fondo.playOnce();
        new GameController().start();
}
}