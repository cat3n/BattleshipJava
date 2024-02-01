import javax.swing.*;
import java.awt.*;
import java.util.Scanner;


public class Battleship {
    public static void main(String[] args){

        // Create an instance of the NameInputDialog class
        SwingUtilities.invokeLater(() -> {
            PlayerSetUp playerSetUp = new PlayerSetUp();
            NameInputDialog nameInputDialog = new NameInputDialog(playerSetUp);
            nameInputDialog.setVisible(true);

        });




    }
}
