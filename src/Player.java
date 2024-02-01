import javax.swing.*;

public class Player {

    private String name;
    private  Ship[] playerShip = new Ship[5];

    private ShipsCreation shipsCreation;


    public Player(String name) {

        this.name = name;
        shipsInitialization();



    }

    private void shipsInitialization(){

        // Create instances of ships with different lengths
        Ship carrier = new Ship(5);
        Ship battleship = new Ship(4);
        Ship destroyer = new Ship(3);
        Ship submarine = new Ship(3);
        Ship patrolBoat = new Ship(2);

        // Assign ships to the array
        playerShip[0] = carrier;
        playerShip[1] = battleship;
        playerShip[2] = destroyer;
        playerShip[3] = submarine;
        playerShip[4] = patrolBoat;

    }

    public Ship[] getPlayerShips() {
        return playerShip;
    }

    public void setPlayerShips(Ship[] playerShip) {
        this.playerShip = playerShip;
    }

}
