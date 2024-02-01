import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PlayerBoard extends JPanel {
    private  JButton[][] pb;
    private ShipState[][] playerBoardState;


    private int rows;
    private int cols;
    private ShipsCreation shipsCreation;
    private  Ship currentShip;

    public PlayerBoard(int rows, int cols, ShipsCreation shipsCreation) {
        this.setLayout(new GridLayout(rows, cols));
        this.shipsCreation = shipsCreation;
        this.currentShip = shipsCreation.getCurrentShip();
        this.initializeBoard(rows, cols);
        this.rows = rows;
        this.cols = cols;
        this.playerBoardState = new ShipState[this.rows][this.cols];
        this.initializePlayerBoardState(rows, cols);

    }

    private void initializeBoard(int rows, int cols) {
        pb = new JButton[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                pb[i][j] = new JButton();
                pb[i][j].setBackground(Color.cyan);
                pb[i][j].addMouseListener(new BoardMouseListener());
                this.add(pb[i][j]);
            }
        }
    }

    private void initializePlayerBoardState(int rows, int cols) {
        this.playerBoardState = new ShipState[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                this.playerBoardState[i][j] = ShipState.EMPTY;
            }
        }
    }

    private void updateStateAndBoard(Ship ship, int[][] shipPositions, ShipState newState) {
        for (int i = 0; i < shipPositions.length; ++i) {
            int[] position = shipPositions[i];
            if (position != null && ship == currentShip && isValidPosition(position[0], position[1])) {
                this.playerBoardState[position[0]][position[1]] = newState;
            }
        }
        this.updatePlayerBoard();
    }

    private int[][] getHorizontalShipPositions(Ship ship, MouseEvent e) {
        int[][] shipPositions = new int[ship.getShipButtons().length][2];
        for (int i = 0; i < ship.getShipButtons().length; ++i) {
            shipPositions[i] = this.calculatePlayerBoardHorizontalPosition(e, i);
        }
        return shipPositions;
    }
    private int[][] getVerticalShipPositions(Ship ship, MouseEvent e) {
        int[][] shipPositions = new int[ship.getShipButtons().length][2];
        for (int i = 0; i < ship.getShipButtons().length; ++i) {
            shipPositions[i] = this.calculatePlayerBoardVerticalPosition(e, i);
        }
        return shipPositions;
    }

    private void placeShip(Ship ship, MouseEvent e) {
        Point mousePosition = this.getMousePosition();
        if (mousePosition != null) {
            Component component = this.getComponentAt(mousePosition);
            if (component instanceof JButton) {
                int[][] shipPositions = new int[0][];
                if (ship.isHorizontal()) {
                    shipPositions = getHorizontalShipPositions(ship, e);
                }
               else{
                    shipPositions = getVerticalShipPositions(ship, e);
                }
                if (isValidPlacement(ship, new Point(shipPositions[0][0], shipPositions[0][1]))) {
                    updateStateAndBoard(ship,shipPositions, ShipState.SHIP_PLACED);
                    ship.setPlaced(true);

                    currentShip = ship;
                    shipsCreation.setCurrentShip(currentShip);
                    shipsCreation.updateShipPlacement(ship, true);
                    //emptyContainer(currentShip);

                }
            }
        }
    }

    private void showPreview(Ship ship, MouseEvent e) {
        Point mousePosition = this.getMousePosition();
        if (mousePosition != null) {
            Component component = this.getComponentAt(mousePosition);
            if (component instanceof JButton) {
                int[][] shipPositions = new int[0][];
                if (ship.isHorizontal()) {
                    shipPositions = getHorizontalShipPositions(ship, e);
                }
                else{
                    shipPositions = getVerticalShipPositions(ship, e);
                }
                if (ship == currentShip) {
                    if (isValidPreview(shipPositions)) {
                        updateStateAndBoard(ship, shipPositions, ShipState.PREVIEW);
                    } else {
                        // If preview is invalid, set the state to INVALID (red)
                        updateStateAndBoard(ship, shipPositions, ShipState.INVALID);
                    }
                }else {
                    // If it's not the current ship, clear the preview state
                    updateStateAndBoard(ship,shipPositions, ShipState.EMPTY);
                }

            }
        }
    }

    private void hidePreview(Ship ship, MouseEvent e) {
        Point mousePosition = this.getMousePosition();
        if (mousePosition != null) {
            Component component = this.getComponentAt(mousePosition);
            if (component instanceof JButton) {
                int[][] shipPositions = new int[0][];
                if (ship.isHorizontal()) {
                    shipPositions = getHorizontalShipPositions(ship, e);
                }
                else{
                    shipPositions = getVerticalShipPositions(ship, e);
                }
                // Check if the ship is placed
                if (!isShipAlreadyPlaced(shipPositions[0])) {
                    // If the ship is not placed, clear the preview
                    updateStateAndBoard(ship,shipPositions, ShipState.EMPTY);
                } else {
                    // If the ship is placed, clear only the preview state
                    for (int i = 0; i < shipPositions.length; ++i) {
                        int[] position = shipPositions[i];
                        if (position != null && this.playerBoardState[position[0]][position[1]] == ShipState.PREVIEW) {
                            this.playerBoardState[position[0]][position[1]] = ShipState.EMPTY;
                            shipsCreation.updateShipPlacement(ship, false);
                        }
                    }
                    updatePlayerBoard(); // Update the board after modifying the states
                }
            }
        }
    }

    private boolean isValidPreview(int[][] shipPositions) {
        for (int i = 0; i < shipPositions.length; ++i) {
            int[] position = shipPositions[i];
            if (position == null || !isValidPosition(position[0], position[1]) || isShipAlreadyPlaced(position)) {
                return false;
            }
        }
        return true;
    }

    private boolean isShipAlreadyPlaced(int[] position) {
        int row = position[0];
        int col = position[1];

        // Check if the indices are within bounds
        if (row >= 0 && row < this.playerBoardState.length && col >= 0 && col < this.playerBoardState[0].length) {
            return this.playerBoardState[row][col] == ShipState.SHIP_PLACED;
        } else {
            return false; // Indices are out of bounds, consider the ship not placed
        }
    }

    private boolean isValidPlacement(Ship ship, Point start) {
        for (int i = 0; i < ship.getShipButtons().length; ++i) {
            int row = start.x;
            int col = start.y + i;
            if (!isValidPosition(row, col)) {
                return false;
            }
            // Check if the position overlaps with a ship placed
            if (this.playerBoardState[row][col] == ShipState.SHIP_PLACED) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPosition(int row, int col) {
        if (isShipAlreadyPlaced(new int[]{row, col})) {
            return false;
        }
        //return row >= 0 && row < pb.length && col >= 0 && col < pb[0].length;
        return row >= 0 && row < this.playerBoardState.length && col >= 0 && col < this.playerBoardState[0].length;
    }

    private int[] calculatePlayerBoardHorizontalPosition(MouseEvent e, int relativeIndex) {
        JButton mouseButton = (JButton) e.getSource();
        int startRow = mouseButton.getY() / pb[0][0].getHeight();
        int startCol = mouseButton.getX() / pb[0][0].getWidth();
        int newCol = startCol + relativeIndex;
        return new int[]{startRow, newCol};
    }
    private int[] calculatePlayerBoardVerticalPosition(MouseEvent e, int relativeIndex) {
        JButton mouseButton = (JButton) e.getSource();
        int startRow = mouseButton.getY() / pb[0][0].getHeight();
        int startCol = mouseButton.getX() / pb[0][0].getWidth();
        int newRow = startRow + relativeIndex;
        return new int[]{newRow, startCol};
    }

    private void updatePlayerBoard() {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                ShipState state = this.playerBoardState[i][j];
                if (i < pb.length && j < pb[0].length) {
                    if (state == ShipState.EMPTY) {
                        pb[i][j].setBackground(Color.cyan);
                    } else if (state == ShipState.PREVIEW) {
                        pb[i][j].setBackground(Color.green);
                    }else if (state == ShipState.INVALID) {
                        pb[i][j].setBackground(Color.red);
                    } else if (state == ShipState.SHIP_PLACED) {
                        pb[i][j].setBackground(Color.gray);
                    }
                }
            }
        }
    }

    private enum ShipState {
        EMPTY,
        PREVIEW,
        INVALID,
        SHIP_PLACED;

        private ShipState() {
        }
    }




    private class BoardMouseListener extends MouseAdapter {
        private BoardMouseListener() {
        }

        public void mouseEntered(MouseEvent e) {

            currentShip = shipsCreation.getCurrentShip();
            if (currentShip != null && !currentShip.isPlaced()) {
                PlayerBoard.this.showPreview(currentShip, e);
            }
        }

        public void mouseExited(MouseEvent e) {
            currentShip = shipsCreation.getCurrentShip();
            if (currentShip != null && !currentShip.isPlaced()) {
                PlayerBoard.this.hidePreview(currentShip, e);
            }
        }

        public void mouseClicked(MouseEvent e) {
            currentShip = shipsCreation.getCurrentShip();
            if (currentShip != null && !currentShip.isPlaced()) {
                PlayerBoard.this.placeShip(currentShip, e);
            }
        }
    }

    public ShipsCreation getShipsCreation(){
        return shipsCreation;
    }
    public void setShipsCreation ( ShipsCreation shipsCreation){
        this.shipsCreation = shipsCreation;
    }
    public void setCurrentShip(Ship currentShip) {
        this.currentShip = currentShip;
    }

}

