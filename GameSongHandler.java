// Foo Yau Yun

import javax.sound.sampled.*;
import java.io.File;

public class GameSongHandler {
    private Clip gameMusicClip; // Clip reference for the game music

    // Method to play the game music
    public void playMusic(String filePath) {
        try {
            File soundFile = new File(filePath); // Path to the song file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            gameMusicClip = AudioSystem.getClip();
            gameMusicClip.open(audioStream);
            gameMusicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error playing game music.");
        }
    }

    // Method to stop the game music
    public void stopMusic() {
        if (gameMusicClip != null && gameMusicClip.isRunning()) {
            gameMusicClip.stop(); // Stop the music
            System.out.println("Game music stopped.");
        }
    }
}