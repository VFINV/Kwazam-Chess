import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeEnterHandler implements ActionListener {
    private final WelcomeView welcomeView;
    private final WelcomeViewSongHandler songHandler;

    public WelcomeEnterHandler(WelcomeView welcomeView, WelcomeViewSongHandler songHandler) {
        this.welcomeView = welcomeView;
        this.songHandler = songHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Stop the background music
        songHandler.stopBackgroundMusic();

        // Close the WelcomeView and start the game
        welcomeView.dispose();  // Close WelcomeView
        new KwazamChess();      // Open the KwazamChess frame
    }
}
