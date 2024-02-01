public class Computer extends Player{

    private Ship[] computerShip = new Ship[5];

    public Computer(String name) {
        super(name);


        computerShip[0] = new Ship(5);
        computerShip[1] = new Ship(4);
        computerShip[2] = new Ship(3);
        computerShip[3] = new Ship(3);
        computerShip[4] = new Ship(2);


    }

}
