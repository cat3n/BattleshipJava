import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ship extends JPanel {
    private JButton[] shipBlocks;
    private boolean placed;

    private boolean h;

    private boolean v;
    private int shipLength; // Added shipLength field



    public Ship(int shipLength) {
        this.shipLength = shipLength; // Set the ship length
        initializeBlocks(shipLength);

    }

    private void initializeBlocks(int shipLength) {
        shipBlocks = new JButton[shipLength];

        for (int i = 0; i < shipLength; i++) {
            JButton button = new JButton();
            button.setMaximumSize(new Dimension(45, 40));
            button.setBackground(Color.GRAY); // Set the background color to gray
            shipBlocks[i] = button;
            add(button);

        }
    }
     void setShipButtonsColor(Color color) {
        for (int i = 0; i < shipBlocks.length; i++) {
            shipBlocks[i].setBackground(color);
        }
    }


    public  JButton[] getShipButtons() {
        return shipBlocks;
    }
    public void setShipButtons(JButton[] shipBlocks){
        this.shipBlocks = shipBlocks;
    }
    public int getLength() {
        return shipLength;
    }

    public boolean isPlaced() {
        return placed;
    }
    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public boolean isHorizontal(){
        return h;
    }
    public void setHorizontal ( boolean h){
        this.h = h;
    }

    public boolean isVertical(){
        return v;
    }
    public void setVertical(boolean v){
        this.v = v;
    }




}