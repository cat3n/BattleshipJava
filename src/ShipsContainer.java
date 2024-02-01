import javax.swing.*;
import java.awt.*;

public class ShipsContainer extends JPanel {
    private JButton[] containerBlocks;


    public ShipsContainer(){

        initializeContainer();
    }
    private void initializeContainer() {
        containerBlocks = new JButton[5];
        for (int i = 0; i < 5; i++) {

                JButton button = new JButton();
                button.setMaximumSize(new Dimension(45, 40));
                containerBlocks[i] = button;
                add(button);

        }
    }
    public  JButton[] getContainerButtons() {
        return containerBlocks;
    }

}
