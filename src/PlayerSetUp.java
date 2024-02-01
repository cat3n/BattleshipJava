import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerSetUp extends JFrame implements PlayerSetUpCallback{


    public static JFrame setUp = new JFrame("BattleShip - Ship's Placement");
    private PlayerBoard playerBoard;
    private Player player;
    private String playerName;
    private NameInputDialog input;




    public void gameBoard() {

        // Create the main JFrame
        setUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUp.setSize(900, 650);

        // Initialize the 'input' field
        input = new NameInputDialog(this);

        playerName = input.getPlayerName();

        this.player = new Player(playerName);

        // Create labels
        JLabel topLabel = new JLabel("Please select ships from the left and place them in your board. Press 'Start Game' when you are ready!");
        JLabel playerLabel = new JLabel("Your Board");
        JLabel shipsLabel = new JLabel("Ships to be placed");


//        // Create a panel for ships and set its layout
//        JPanel shipsPanel = ShipsCreation.initializeBlocks();
//        shipsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));


        // Create button for the bottom-left corner
        JButton rotateButton = new JButton("Rotate Ship");
        rotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle the orientation of the ship
                Ship currentShip = playerBoard.getShipsCreation().getCurrentShip();
                boolean currentOrientation = currentShip.isHorizontal();
                currentShip.setHorizontal(!currentOrientation);
                currentShip.setVertical(currentOrientation);
            }
        });

        // Create start button for the bottom-right corner
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform start action when the button is clicked
                // Add your start logic here

            }
        });

        // Initialize ships panel
        ShipsCreation shipsCreation = new ShipsCreation(player);
        JPanel shipsPanel = shipsCreation.getContainerPanel();
        //shipsCreation.getShips()[4].getShipButtons()[0].setBackground(Color.RED);

        // Create player board
        playerBoard = new PlayerBoard(10, 10, shipsCreation);




        // Creation of the main panel

        // Create a panel for the top label
        JPanel topLabelPanel = new JPanel();
        topLabelPanel.add(topLabel);
        topLabelPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0)); // Adjust the bottom spacing as needed

        // Create a panel for the bottom buttons
        JPanel bottomButtonPanel = new JPanel(new BorderLayout());
        bottomButtonPanel.add(rotateButton, BorderLayout.WEST);
        bottomButtonPanel.add(startButton, BorderLayout.EAST);
        bottomButtonPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Adjust the height as needed
        bottomButtonPanel.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 5)); // Adjust the top spacing as needed



        // Create a panel for the left side
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 0));
        leftPanel.add(shipsLabel, BorderLayout.NORTH );
        shipsLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(shipsPanel, BorderLayout.CENTER);
        //leftPanel.add(containerPanel, BorderLayout.CENTER);
        leftPanel.setPreferredSize(new Dimension(300, 600));


        // Create a panel for the right side
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20)); // Adjust the top and bottom spacing as needed
        // Add playerLabel to the top of rightPanel
        rightPanel.add(playerLabel);
        playerLabel.setHorizontalAlignment(JLabel.CENTER);
        // Add playerPanel to the center of rightPanel
        rightPanel.add(playerBoard);
        // Set preferred size for the right panel (containing playerPanel)
        rightPanel.setPreferredSize(new Dimension(500, 500)); // Set your desired width and height


        // Create the main panel and set its layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topLabelPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

        // Add the main panel to the main frame
        setUp.add(mainPanel);

        // Make the main frame visible
        setUp.setVisible(true);

        setUp.setResizable(false);
    }


    @Override
    public void onPlayerNameEntered(String playerName) {
        gameBoard();
    }
    public PlayerBoard getPlayerBoard(){
        return playerBoard;
    }
}
