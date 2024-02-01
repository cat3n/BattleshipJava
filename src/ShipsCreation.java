import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShipsCreation extends JPanel{

    private Ship[] ships;
    private Player player;
    private PlayerSetUp playerSetUp = new PlayerSetUp();
    private ShipsContainer[] shipsContainer ;

    private ShipsContainer currentContainer;
    private JPanel containerPanel;
    private JPanel[] container = new JPanel[5];

    private JButton[] containerButtons;
    private JButton cbutton;
    private JButton[] shipButtons;
    private JButton sbutton;
    private  Ship currentShip;

    public ShipsCreation(Player player){
        this.player = player;
        initializeContainer();

    }



    private void initializeShips() {

        ships = player.getPlayerShips();

        // Loop through each ship
        for (int i = 0; i < 5; i++) {
            Ship currentShip = ships[i];

            // Loop through each button in the ship
            for(int j =0; j< currentShip.getShipButtons().length; j++ ){

                // Get the button from the current ship
                JButton button = currentShip.getShipButtons()[j];

                // Add an action listener to the button with the current ship
                //button.addActionListener(createActionListener(currentShip));


            }

        }

    }

     private void initializeContainer(){

        // Call initializeShips to ensure ships are initialized
       // initializeShips();
        ships = player.getPlayerShips();

        //Initialize ships container
        shipsContainer = new ShipsContainer[5];
        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

         // Initialize each element of the container array Panel
         for (int i = 0; i < container.length; i++) {
             container[i] = new JPanel();
             container[i].setLayout(new BoxLayout(container[i], BoxLayout.X_AXIS));
             container[i].setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
         }


        //for every ship / container
        for (int i = 0; i < ships.length; i++) {
            shipsContainer[i] = new ShipsContainer();
            ShipsContainer currentContainer = shipsContainer[i];
            containerButtons = currentContainer.getContainerButtons();

            Ship currentShip = ships[i];
            shipButtons = currentShip.getShipButtons();
            // Loop through each button in the ship
            for (int j = 0; j < shipButtons.length; j++) {
                // Get the button from the ship
                shipButtons[j] = sbutton;
                sbutton.setBackground(Color.GRAY);
                sbutton.addActionListener(createActionListener());
                // Add the button to the container
                container[i].add(sbutton);
            }

            // Add remaining container buttons if any
            for (int j = shipButtons.length; j < containerButtons.length; j++) {
                cbutton = containerButtons[j];
                container[i].add(cbutton);
            }
            // Add the container to the container panel
            containerPanel.add(container[i]);
        }
        // Add the container panel to the ShipsCreation panel
        add(containerPanel);
    }

    public void updateShipPlacement(Ship currentShip, boolean isPlaced) {
        currentShip.setPlaced(isPlaced);
    }


    private ActionListener createActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the clicked button
                JButton clickedButton = (JButton) e.getSource();
                // Set the ship buttons to yellow
                JButton[] shipButtons = currentShip.getShipButtons();

                clickedButton.setBackground(Color.YELLOW);
                // Set other ships' buttons to gray and clickable
                setButtonsClickableForOtherShips(clickedButton);
                // Set the current ship
                setCurrentShip(currentShip);

            }
        };
    }


    private void setButtonsClickableForOtherShips(JButton clickedButton) {
        // Loop through each ship
        JButton[] currentButton = new JButton[ships.length];
        for (int i = 0; i < ships.length; i++) {
           /* Ship ship = ships[i];
            // Check if the ship is not the current ship
            if (ship != currentShip) {
                // Reset other ships' buttons to gray
                ship.setShipButtonsColor(Color.GRAY);
            }*/
            clickedButton = currentButton[i];
            if (clickedButton != )
        }
        revalidate();
        repaint();
    }

    public JPanel getContainerPanel(){
        return containerPanel;
     }
     public void setContainerPanel(JPanel containerPanel){
        this.containerPanel = containerPanel;
     }
     public JPanel[] getContainer(){
        return container;
     }

     public void setContainer( JPanel[] container){
        this.container = container;
     }

    public Ship[] getShips() {
        return ships;
    }

    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

    public ShipsContainer[] getShipsContainer(){
        return shipsContainer;
    }

    public void setShipsContainer(ShipsContainer[] shipsContainer){
        this.shipsContainer = shipsContainer;
    }
    public void setCurrentShip(Ship ship) {
        currentShip = ship;
    }
    public Ship getCurrentShip() {
        return currentShip;
    }

    public void setCurrentContainer(ShipsContainer currentContainer){
        this.currentContainer = currentContainer;
    }

    public ShipsContainer getCurrentContainer(){
        return currentContainer;
    }
    public JButton getShipButtonsFromPanel(){
        return sbutton;
    }
    public void setShipButtonFromPanel(JButton sbutton){
        this.sbutton = sbutton;

    }

    public JButton getContButtonsFromPanel(){
        return cbutton;
    }
    public void setContButtonsFromPanel(JButton cbutton){
        this.cbutton = cbutton;
    }


}
