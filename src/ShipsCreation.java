import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShipsCreation extends JPanel{

    private Ship[] ships;
    private Ship[] display;
    private Player player;
    private PlayerSetUp playerSetUp = new PlayerSetUp();
    private PlayerBoard playerBoard;
    private ShipsContainer[] shipsContainer ;
    private boolean active;

    private ShipsContainer currentContainer;
    private JPanel containerPanel;
    private JPanel[] container = new JPanel[5];

    private JButton[] containerButtons;
    private JButton cbutton;
    private JButton[] shipButtons;
    private JButton[] activeButtons;
    private JButton sbutton;
    private  Ship currentShip;
    private Ship displayedShip;

    public ShipsCreation(Player player){
        this.player = player;
        initializeContainer();

    }



    private void initializeShips() {

        ships = player.getPlayerShips();

        // Initialize display array
        display = new Ship[5];
        // Create instances of ships with different lengths

        display[0] = new Ship(5);
        display[1] = new Ship(4);
        display[2] = new Ship(3);
        display[3] = new Ship(3);
        display[4] = new Ship(2);


        // Loop through each ship
        for (int i = 0; i < 5; i++) {
            Ship displayedShip = display[i];

            // Loop through each button in the ship
            for(int j = 0; j< displayedShip.getShipButtons().length; j++ ){
                // Get the button from the current ship
                JButton button = displayedShip.getShipButtons()[j];
                // Add an action listener to the button with the current ship
                //button.addActionListener(createActionListener(currentShip));
                // Add the button to the ShipsCreation panel
                //add(button);
            }

        }

    }

     private void initializeContainer(){

        // Call initializeShips to ensure ships are initialized
        initializeShips();


        //Initialize ships container
        shipsContainer = new ShipsContainer[5];
        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

         // Initialize each element of the container array
         for (int i = 0; i < container.length; i++) {
             container[i] = new JPanel();
             container[i].setLayout(new BoxLayout(container[i], BoxLayout.X_AXIS));
             container[i].setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
         }


        //for every ship / container
        for (int i = 0; i < display.length; i++) {
            shipsContainer[i] = new ShipsContainer();
            ShipsContainer currentContainer = shipsContainer[i];
            containerButtons = currentContainer.getContainerButtons();

            currentShip = ships[i];
            activeButtons = currentShip.getShipButtons();
            // Loop through each button in the ship
            displayedShip = display[i];
            shipButtons = displayedShip.getShipButtons();
            for (int j = 0; j < shipButtons.length; j++) {
                // Get the button from the ship
                shipButtons[j].addActionListener(createActionListener(displayedShip,currentShip));
                // Add the button to the container
                container[i].add(shipButtons[j]);
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
         System.out.println("dis: " + displayedShip);
         System.out.println("curr: " + currentShip);
    }

    public void updateShipPlacement(Ship currentShip, boolean isPlaced) {
        currentShip.setPlaced(isPlaced);
    }

  /*  public boolean isActive(){

        for( int i =0; i<ships.length; i++) {
            currentShip = ships[i];
            // Set the current ship
        }
        setCurrentShip(currentShip);
        return active;
    }*/

   /* public void setActive(boolean active){
        this.active = active;
    }*/


    private ActionListener createActionListener(Ship displayedShip, Ship currentShip) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the clicked button
                JButton clickedButton = (JButton) e.getSource();
                // Set the ship buttons to yellow
                //JButton[] shipButtons = currentShip.getShipButtons();

                displayedShip.setShipButtonsColor(Color.YELLOW);
                // Set other ships' buttons to gray and clickable
                setButtonsClickableForOtherShips(displayedShip);
                //set the displayed ship as active
                //setActive(true);
                setCurrentShip(currentShip);
                // Provide the updated current ship to PlayerBoard
                PlayerBoard playerBoard = playerSetUp.getPlayerBoard();
                if (playerBoard != null) {
                    playerBoard.setCurrentShip(getCurrentShip());
                }



            }
        };
    }


    private void setButtonsClickableForOtherShips(Ship displayedShip) {
        // Loop through each ship
        for (int i = 0; i < display.length; i++) {
            Ship ship = display[i];
            // Check if the ship is not the current ship
            if (ship != displayedShip) {
                // Reset other ships' buttons to gray
                ship.setShipButtonsColor(Color.GRAY);
            }
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

    public Ship[] getDisplayShips(){
        return display;
    }
    public void setDisplayShips(Ship[] display){
        this.display=display;
    }

    public ShipsContainer[] getShipsContainer(){
        return shipsContainer;
    }

    public void setShipsContainer(ShipsContainer[] shipsContainer){
        this.shipsContainer = shipsContainer;
    }
    public Ship getDisplayedShip(){
        return  displayedShip;
    }
    public void setDisplayedShip(Ship display) {
        displayedShip = display;
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

    public void setPlayerBoard(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }

    public PlayerBoard getPlayerBoard(){
        return playerBoard;
    }


}
