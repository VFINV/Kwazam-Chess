import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class WelcomeView extends JFrame {
    private Image backgroundImage;
    private Clip backgroundMusicClip;
    private WelcomeViewSongHandler songHandler;

    public WelcomeView() {
        // Initialize the music handler
        songHandler = new WelcomeViewSongHandler("WelcomeViewBackgroundMusic.wav");
        songHandler.playBackgroundMusic();

        // Attempt to load the background image
        try {
            File imgFile = new File("WelcomeViewBackground1.jpg");
            System.out.println("Loading image from: " + imgFile.getAbsolutePath()); // Debugging print statement
            backgroundImage = ImageIO.read(imgFile);
            if (backgroundImage == null) {
                System.out.println("Image is null, check the file path.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading image.");
        }

        // Set up the Welcome View frame
        setTitle("Welcome to Kwazam Chess");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Create the welcome page with GridBagLayout
        JPanel welcomePage = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Stretch the image to fit the panel
                }
            }
        };

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to Kwazam Chess!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Set Arial font with size 36
        titleLabel.setForeground(Color.WHITE); // Make text color visible on background

        // Enter Game Button
        JButton enterButton = new JButton("Enter Game");
        enterButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Set Arial font with size 24
        enterButton.setPreferredSize(new Dimension(250, 80)); // Make the button larger

        // Add WelcomeEnterHandler to the "Enter Game" button
        enterButton.addActionListener(new WelcomeEnterHandler(this, songHandler));

        // Rules Button
        JButton rulesButton = new JButton("Rules");
        rulesButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Set Arial font with size 24
        rulesButton.setPreferredSize(new Dimension(250, 80)); // Make the button larger

        // Add an ActionListener to the "Rules" button
        rulesButton.addActionListener(new RulesHandler());

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Set Arial font with size 24
        exitButton.setPreferredSize(new Dimension(250, 80)); // Make the button larger

        // Use GameExitHandler for the Exit button
        GameExitHandler exitHandler = new GameExitHandler();
        exitButton.addActionListener(exitHandler);

        // GridBagConstraints to position the components
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20, 10, 20, 10); // Add spacing
        welcomePage.add(titleLabel, constraints);

        constraints.gridy = 1; // Move to the next row
        welcomePage.add(enterButton, constraints);

        constraints.gridy = 2; // Add the Rules button below Enter Game button
        welcomePage.add(rulesButton, constraints);

        constraints.gridy = 3; // Move to the next row for Exit button
        welcomePage.add(exitButton, constraints);

        // Add the welcomePage to the frame
        add(welcomePage);

        // Make the frame visible
        setVisible(true);
    }
}
