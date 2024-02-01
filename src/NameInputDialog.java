import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NameInputDialog extends JFrame {
    private JLabel nameLabel;
    private JTextField nameTextField;
    private String playerName;
    private PlayerSetUpCallback callback; // Callback interface

    private PlayerSetUp playerSetUp;
    public NameInputDialog(PlayerSetUpCallback callback) {
        this.callback = callback;
        // Set the title of the window
        setTitle("Battleship - Player's Details");
        setResizable(false);
        // Create components
        nameLabel = new JLabel("Please insert your name and press 'OK'");
        nameTextField = new JTextField(20);
        JButton okButton = new JButton("OK");

        //Set layout manager for the panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to the panel with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(nameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(nameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(Box.createVerticalStrut(10), gbc); // Add vertical spacing

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(okButton, gbc);

        // Add action listener to the OK button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered name from the text field
                playerName = nameTextField.getText();

                // Store and use the player's name
                if (!playerName.isEmpty()) {
                    // Update the title of the JFrame with the player's name
                    //PlayerSetUp.player.setTitle(playerName + "'s Board");

                    callback.onPlayerNameEntered(playerName);

                    dispose();
                } else {
                    // Handle case when the player enters an empty name
                    JOptionPane.showMessageDialog(null, "Please enter a valid name.");
                }

            }
        });

        // Set default close operation and size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 150);

        setLocationRelativeTo(null); // Center the window on the screen

    }

    public String getPlayerName(){
        return playerName;
    }
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }
}
