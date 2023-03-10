package com.brandonRasgaitis;

/**
 * Class for creating the 3-D game board.
 */
public class GameBoard {
    private Player[][] frontLayer = new Player[3][3];
    private Player[][] middleLayer = new Player[3][3];
    private Player[][] backLayer = new Player[3][3];

    /**
     * @param index takes in an integer as a parameter
     * @return (index % 3) - 1 in order to get the column for the board
     */
    public int findColumn(int index) {
        if ((index % 3) - 1 < 0) {
            return 2;
        }
        return (index % 3) - 1;
    }

    /**
     * @param index takes in an integer as a parameter
     * @return 2, in order to get the row for the game board
     */
    public int findRow(int index) {
        if (index >= 0 && index <= 3) {
            return 0;
        }
        if (index >= 4 && index <= 6) {
            return 1;
        }
        return 2;
    }

    /**
     * @param layer Takes in a string as a parameter
     * @param space Takes in an integer as a parameter
     * @return null
     * Creates the layers for the Gameboard
     */
    public String getSpace(final String layer, final int space) {
        if (layer.equalsIgnoreCase("a")) {
            int rowIndex = findRow(space);
            int colIndex = findColumn(space);
            if (frontLayer[rowIndex][colIndex] == null) {
                return null;
            }
            return frontLayer[rowIndex][colIndex].getName();
        }
        else if (layer.equalsIgnoreCase("b")) {
            int rowIndex = findRow(space);
            int colIndex = findColumn(space);

            if (middleLayer[rowIndex][colIndex] == null) {
                return null;
            }
            return middleLayer[rowIndex][colIndex].getName();
        }
        else if (layer.equalsIgnoreCase("c")) {
            int rowIndex = findRow(space);
            int colIndex = findColumn(space);

            if (backLayer[rowIndex][colIndex] == null) {
                return null;
            }
            return backLayer[rowIndex][colIndex].getName();
        }
        return null;
    }

    /**
     * Names the layers of the gameboard as well as gets the array layer
     */
    public void getBoard() {
        System.out.println("Layer A");

        for (Player[] layer: frontLayer) {
            for (Player player: layer) {
                if (player == null) {
                    System.out.println("    ");
                    continue;
                }
                System.out.print(player.toString() + " ");
            }
            System.out.println();
        }
        System.out.println("\nLayer B");
        for (Player[] layer: middleLayer) {
            for (Player player: layer) {
                if (player == null) {
                    System.out.println("    ");
                    continue;
                }
                System.out.print(player.toString() + " ");
            }
            System.out.println();
        }
        System.out.println("\nLayer C");
        for (Player[] layer: backLayer) {
            for (Player player: layer) {
                if (player == null) {
                    System.out.println("    ");
                    continue;
                }
                System.out.print(player.toString() + " ");
            }
            System.out.println();
        }
    }

    /**
     * @param layer Takes in a String as a parameter
     *              gets the boards specific layer
     */
    public void getBoardAt(final String layer) {
        if (layer.equalsIgnoreCase("a")) {
            System.out.println("Layer A");
            for (Player[] arr: frontLayer) {
                for (Player player: arr) {
                    if (player == null) {
                        System.out.println("    ");
                        continue;
                    }
                    System.out.print(player.toString() + " ");
                }
                System.out.println();
            }
        }
        else if (layer.equalsIgnoreCase("b")) {
            System.out.println("\nLayer B");
            for (Player[] arr: frontLayer) {
                for (Player player: arr) {
                    if (player == null) {
                        System.out.println("    ");
                        continue;
                    }
                    System.out.print(player.toString() + " ");
                }
                System.out.println();
            }
        }
        else {
            System.out.println("\nLayer C");
            for (Player[] arr: frontLayer) {
                for (Player player: arr) {
                    if (player == null) {
                        System.out.println("    ");
                        continue;
                    }
                    System.out.print(player.toString() + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * @param player Takes in the the Class object player as a parameter
     * @param layer Takes in a string as a parameter
     * @param index Takes in an integer as a parameter
     * @return false if the player move is not an acceptable move, other wise returns true
     */
    public boolean makeMove(final Player player, final String layer, final int index) {
        if (layer.equalsIgnoreCase("a")) {
            int row = findRow(index);
            int column = findColumn(index);

            if (frontLayer[row][column] != null) {
                return false;
            }
            else {
                frontLayer[row][column] = player;
                return true;
            }
        }
        else if (layer.equalsIgnoreCase("b")) {
            int row = findRow(index);
            int column = findColumn(index);

            if (middleLayer[row][column] == null) {
                middleLayer[row][column] = player;
                return true;
            }
        }
        else if (layer.equalsIgnoreCase("c")) {
            int row = findRow(index);
            int column = findColumn(index);

            if (backLayer[row][column] == null) {
                backLayer[row][column] = player;
                return true;
            }
        }
        return false;
    }

    /**
     * @param players Takes in a double array players as a parameter
     * @return null if their isn't a winner yet
     * Method checks for horizontal, vertical, and diagonal wins.
     */
    private Player getWinnerForAFace(Player[][] players) {
        for (int i = 0; i < 2; i++) {
            if (players[i][0] != null && players[i][0].equals(players[i][1]) && players[i][0].equals(frontLayer[i][2])) {
                return players[i][0];
            }
        }
        for (int i = 0; i < 2; i++) {
            if (players[0][i] != null && players[0][i].equals(players[1][i]) && players[0][i].equals(players[2][i])) {
                return players[0][i];
            }
        }
        if (players[1][1] == null) {
            return null;
        }
        if (players[0][0] != null && players[0][0].equals(players[1][1]) && players[0][0].equals(players[2][2])) {
            return players[0][0];
        }
        if (players[0][2] != null && players[0][2].equals(players[1][1]) && players[0][2].equals(players[2][0])) {
            return players[0][2];
        }
        return null;
    }

    /**
     * @return null if their is no winner yet during the game
     */
    public Player getWinner() {
        if (getWinnerForAFace(frontLayer) != null) {
            return getWinnerForAFace(frontLayer);
        }
        if (getWinnerForAFace(middleLayer) != null) {
            return getWinnerForAFace(middleLayer);
        }
        if (getWinnerForAFace(backLayer) != null) {
            return getWinnerForAFace(backLayer);
        }
        // temp arrays for slices of the cubic gameBoard
        Player[][] playerArray = new Player[3][3];
        // top slice
        for (int i = 0; i < 3; i++) {
            playerArray[2][i] = frontLayer[0][i];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[1][i] = middleLayer[0][i];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[0][i] = backLayer[0][i];
        }
        if (getWinnerForAFace(playerArray) != null) {
            return getWinnerForAFace(playerArray);
        }
        // middle horizontal slice
        for (int i = 0; i < 3; i++) {
            playerArray[2][i] = frontLayer[1][i];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[1][i] = middleLayer[1][i];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[0][i] = backLayer[1][i];
        }
        if (getWinnerForAFace(playerArray) != null) {
            return getWinnerForAFace(playerArray);
        }
        // bottom slice
        for (int i = 0; i < 3; i++) {
            playerArray[2][i] = frontLayer[2][i];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[1][i] = middleLayer[2][i];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[0][i] = backLayer[2][i];
        }
        if (getWinnerForAFace(playerArray) != null) {
            return getWinnerForAFace(playerArray);
        }
        // left slice
        for (int i = 0; i < 3; i++) {
            playerArray[i][0] = frontLayer[i][0];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[i][1] = middleLayer[i][0];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[i][2] = backLayer[i][0];
        }
        if (getWinnerForAFace(playerArray) != null) {
            return getWinnerForAFace(playerArray);
        }
        // middle vertical slice
        for (int i = 0; i < 3; i++) {
            playerArray[i][0] = frontLayer[i][1];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[i][1] = middleLayer[i][1];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[i][2] = backLayer[i][1];
        }
        if (getWinnerForAFace(playerArray) != null) {
            return getWinnerForAFace(playerArray);
        }
        // right slice
        for (int i = 0; i < 3; i++) {
            playerArray[i][0] = frontLayer[i][2];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[i][1] = middleLayer[i][2];
        }
        for (int i = 0; i < 3; i++) {
            playerArray[i][2] = backLayer[i][2];
        }
        if (getWinnerForAFace(playerArray) != null) {
            return getWinnerForAFace(playerArray);
        }
        if (middleLayer[1][1] == null) {
            return null;
        }
        if (frontLayer[0][0] != null && frontLayer[0][0].equals(middleLayer[1][1])
                && frontLayer[0][0].equals(backLayer[2][2])) {
            return frontLayer[0][0];
        }
        if (backLayer[0][2] != null && backLayer[0][2].equals(middleLayer[1][1])
                && backLayer[0][2].equals(frontLayer[2][0])) {
            return backLayer[0][2];
        }
        if (frontLayer[0][2] != null && frontLayer[0][2].equals(middleLayer[1][1])
                && frontLayer[0][2].equals(backLayer[2][0])) {
            return frontLayer[0][2];
        }
        if (backLayer[0][0] != null && backLayer[0][0].equals(middleLayer[1][1])
                && backLayer[0][0].equals(frontLayer[2][2])) {
            return backLayer[0][0];
        }
        return null;
    }
}