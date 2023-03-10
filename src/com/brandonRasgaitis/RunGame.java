package com.brandonRasgaitis;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CISC 114
 * @author Brandon Rasgaitis
 * @version 12.0 - July 31, 2019
 */
public class RunGame {

    static Player[] players = new Player[2];
    static GameBoard gameBoard = new GameBoard();
    static int playerTurn = (int) Math.round(Math.random());
    static boolean gameEnd = false;

    /**
     * @param args Outputs the game of Quibic.
     * @throws Exception input mismatch exception
     * if a person gets their name either diagonally, vertically, or horizontally on any of the three layers they win the game
     * Game Loops until a player wins
     *
     */
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter player names.");

        for (int i = 0; i < 2; i++) {
            String name = scan.nextLine();
            players[i] = new Player(name);
        }
        System.out.println(getInstructions());

        while (!gameEnd) {
            System.out.printf("It is %s's turn.\n", players[playerTurn].getName());
            String userInput = scan.next();
            System.out.println("Player input: " + userInput);
            if (userInput.equalsIgnoreCase("help")) { // get instructions
                System.out.println(getInstructions());
            }
            else if (userInput.equalsIgnoreCase("move")) { // move
                String layer;
                int space;

                try {
                    layer = scan.next();
                    space = scan.nextInt();
                }
                catch (InputMismatchException exception) {
                    System.out.println("Bad input.");
                    continue;
                }
                System.out.println("Layer: " + layer);
                System.out.println("Space: " + space);

                while (!layer.equalsIgnoreCase("a") ^ !layer.equalsIgnoreCase("b")
                        ^ !layer.equalsIgnoreCase("c") ^ !(space >= 1 && space <= 9)) {
                    System.out.println("Please input a valid move. \n"
                            + "Layer should be a, b, or c and space should be "
                            + "an integer number between 1 and 9 (Ex. a 1)");
                    layer = scan.next();
                    space = scan.nextInt();
                }
                boolean successfulMove = gameBoard.makeMove(players[playerTurn], layer, space);
                System.out.println(gameBoard.getSpace(layer, space));
                System.out.println(successfulMove);
                if (!successfulMove) {
                    System.out.println("That space is already taken.");
                    continue;
                }
                nextTurn();
                gameEnd = gameBoard.getWinner() != null;
            }
            else if (userInput.equalsIgnoreCase("getb")) { // get gameBoard
                gameBoard.getBoard();
            }
            else if (userInput.equalsIgnoreCase("gtba")) { // get gameBoard at
                String layer = scan.next();

                while (!layer.equalsIgnoreCase("a") || !layer.equalsIgnoreCase("b") || !layer.equalsIgnoreCase("c")) {
                    System.out.println("Please input a valid layer a, b, or c. (Ex. a)");
                    layer = scan.next();
                }
                gameBoard.getBoardAt(layer);
            }
            else {
                System.out.println("Please input a valid command." + getCommands());
            }
        }
        nextTurn();
        System.out.printf("%s has won the game.", players[playerTurn].getName());
    }

    /**
     * @return A menu explaining how to play the game
     */
    private static String getInstructions() {
        return "There are three layers. \n\n"
                + "Layer 'a', 'b', 'c'.  Where layer 'a' \n"
                + "faces you the Player, and layer 'c' is the farthest most layer. \n\n"
                + "Each layer has an assignment position 1-9 similar to \n"
                + "your phone dial pad. \n\n"
                + "These are the commands: \n"
                + "\t1.) move (a, b, or c) (1 - 9) - makes  a move (Ex. move a 1)\n"
                + "\t2.) help - get instructions \n"
                + "\t3.) getb - prints all three layers of the gameBoard \n"
                + "\t4.) gtba (a, b, or c) - prints the specified layer (Ex. gtba a)\n";
    }

    /**
     * @return a menu explaining the controls of the game
     */
    private static String getCommands() {
        return "These are the commands: \n"
                + "move (a, b, or c) (1 - 9)- makes  a move (Ex. move a 1)\n"
                + "help - get instrusctions \n"
                + "getb - prints all three layers of the gameBoard \n"
                + "gtba (a, b, or c)- prints the specified layer (Ex. gtba a)";
    }

    /**
     * gets the next players turn
     */
    private static void nextTurn() {
        playerTurn++;
        if (playerTurn % 2 == 0) {
            playerTurn = 0;
        }
    }
}