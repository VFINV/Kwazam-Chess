// Leticia Lai Suh Hooi 

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private GameBoard board; // Reference to the game board
    private GameSongHandler songHandler; // Reference to the GameSongHandler
    private JButton[][] buttons = new JButton[8][5]; // Array of buttons representing the board's grid
    private JButton pauseButton = new JButton("Pause"); // Button to pause the game
    private JButton startButton = new JButton("Start"); // Button to start the game
    private static final Color START_BUTTON_COLOR = new Color(168, 217, 237);
    private static final Color PAUSE_BUTTON_COLOR = new Color(234, 98, 105);
    private JPanel gamePanel = new JPanel(new GridLayout(8,5)); // Panel to hold the board's buttons
    private JPanel topPanel = new JPanel(new BorderLayout());
    private JPanel statusPanel = new JPanel(); 
    private JPanel buttonPanel = new JPanel (new FlowLayout());
    private JMenuBar menuBar = new JMenuBar();
    private JLabel labelInstruction = new JLabel(); // Label to show game instructions
    private JLabel labelTeam = new JLabel(); // Label to show the current team's turn
    private JLabel labelRedCount = new JLabel(); // Label to show Red team's step count
    private JLabel labelBlueCount = new JLabel(); // Label to show Blue team's step count

    // Constructor that initializes the game view and links it with the board
    GameView(String name, GameBoard board) {
        super(name);
        this.board = board;
        
        // Initialize GameSongHandler
        songHandler = new GameSongHandler();
        songHandler.playMusic("GamePlayingSong.wav"); // Start playing game music
        
        // Set button colors
        startButton.setBackground(START_BUTTON_COLOR);
        pauseButton.setBackground(PAUSE_BUTTON_COLOR);
        
        // Add listener to buttons
        startButton.addActionListener(new GameStartHandler(this, board)); 
        pauseButton.addActionListener(new GamePauseHandler(this));
        
        // Group items into panels
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        
        // Set the layout for the status panel to BoxLayout for vertical stacking
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        
        // Add the labels to the status panel
        statusPanel.add(labelInstruction);
        statusPanel.add(labelTeam);
        statusPanel.add(labelRedCount); 
        statusPanel.add(labelBlueCount); 
        topPanel.add(statusPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.PAGE_START);
        
        // Initialize the buttons on the game panel
        initializeButtons();
        
        // Configure the UI layout
        configureUI();
        
    }
    
    // Method to update the red and blue step counts
    public void updateStepCounts() {
        labelRedCount.setText("Red Steps: " + board.getRedCount()); // Update Red step count label
        labelBlueCount.setText("Blue Steps: " + board.getBlueCount()); // Update Blue step count label
    }
    
    // Initializes the 2D array of buttons that represent the game board
    private void initializeButtons() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 5; j++) {
                initializeButton(i, j);
            }
        }
    }
    
    // Initializes individual buttons at specific positions on the board
    private void initializeButton(int i, int j) {
        buttons[i][j] = new JButton();
        buttons[i][j].setActionCommand(i + " " + j);
        buttons[i][j].setBackground(Color.WHITE); // Default color for the button
        gamePanel.add(buttons[i][j]);
    }
    
    // Configures the overall UI: labels, buttons, menu bar
    private void configureUI() {
        labelInstruction.setText("Click On Start To Play!");
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(600,800));
        addMenu(); // Adds menu items to the menu bar
        setJMenuBar(menuBar); // Sets the menu bar
        add(topPanel, BorderLayout.NORTH); // Adds the top panel with status and buttons
        add(gamePanel, BorderLayout.CENTER); // Adds the game board
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Updates the instruction label (game instructions)
    public void setLabelInstruction(String instruction){ 
        labelInstruction.setText(instruction);
    }

     // Update the current team and also call updateStepCounts to show red/blue step counts
    public void setLabelTeam(String team, Color color) {
        labelTeam.setText(team);
        labelTeam.setForeground(color);
        updateStepCounts(); // Update the step counts whenever the team changes
    }
    
    // Adds items to the menu bar and associates listeners for the menu items
    public void addMenu() {
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem newGame = new JMenuItem("New Game");
        fileMenu.add(newGame);
        newGame.addActionListener(new GameNewHandler(board, this));

        JMenuItem saveGame = new JMenuItem("Save Game");
        fileMenu.add(saveGame);
        saveGame.addActionListener(new GameSaveHandler(board, this));
        
        JMenuItem loadGame = new JMenuItem("Load Game");
        fileMenu.add(loadGame);
        loadGame.addActionListener(new GameLoadHandler(board, this));
        
        JMenuItem home = new JMenuItem("Home");
        fileMenu.add(home);
        home.addActionListener(new GameHomeHandler(this));
        
        JMenuItem exit = new JMenuItem("Exit");
        fileMenu.add(exit);
        exit.addActionListener(new GameExitHandler());

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem about = new JMenuItem("Rules");
        helpMenu.add(about);
        about.addActionListener(new RulesHandler());
    }

    // Adds action listeners to all buttons on the game board
    public void addListener() {
        deleteListener(); // Removes existing listeners before adding new ones
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 5; j++) {
                buttons[i][j].addActionListener(new PlayerClickHandler(board, this));
            }
        }
    }

    // Removes all action listeners from buttons on the game board
    public void deleteListener() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 5; j++) {
                for(ActionListener g : buttons[i][j].getActionListeners()) {
                    buttons[i][j].removeActionListener(g);
                }
            }
        }
    }

    // Resets the game board and UI components to their initial state
    public void initialize() {
        deleteListener();
        PlayerClickHandler.setHasSelectedPiece(false); // Reset selected piece state
        labelInstruction.setText("Click On Start To Play!"); // Reset instruction label
        labelTeam.setText(""); // Clear the team label
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 5; j++) {
                buttons[i][j].setBackground(Color.white); // Reset button colors
            }
        }
        updateView(); // Update the game view with the current board state
    }

    // Updates the view by setting icons for each button based on the board's current state
    public void updateView() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 5; j++) {
                buttons[i][j].setIcon(null); // Remove existing icons
            }
        }
        
        // Set icons for each button based on the pieces on the board
        for(int i = 0; i < 20; i++) {
            if((board.getPiece(i).getY() >= 0 && board.getPiece(i).getY() < 8) && (board.getPiece(i).getX() >= 0 && board.getPiece(i).getX() < 5)) {
                buttons[board.getPiece(i).getY()][board.getPiece(i).getX()].setIcon(board.getPiece(i).getIcon());
            }
        }
    }

    // Changes the background color of a specific button on the game board
    public void changeBoardColor(int x, int y, Color color) {
        buttons[y][x].setBackground(color);
    }
    
    // Displays a dialog box with a given message
    public void gameDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    // Stops the background music
    public void stopBackgroundMusic() {
        songHandler.stopMusic();
    }
    
}
