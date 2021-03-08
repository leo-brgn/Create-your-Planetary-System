import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class TitleMusic {

    public TitleMusic () throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        File file = new File("items/sounds/title-music.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}