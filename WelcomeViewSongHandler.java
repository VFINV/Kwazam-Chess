import javax.sound.sampled.*;
import java.io.File;

public class WelcomeViewSongHandler {
    private Clip backgroundMusicClip;
    private final String musicFilePath;

    public WelcomeViewSongHandler(String musicFilePath) {
        this.musicFilePath = musicFilePath;
    }

    // Method to play the background music
    public void playBackgroundMusic() {
        try {
            File soundFile = new File(musicFilePath); // Music file path
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(audioStream);
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error playing background music.");
        }
    }

    // Method to stop the background music
    public void stopBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop(); // Stop the music
        }
    }
}
