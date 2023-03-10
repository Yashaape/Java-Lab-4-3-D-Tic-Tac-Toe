package com.brandonRasgaitis;

/** Class variable that we used to generate a unique ID for each newly created player. */
public class Player {
    private static int globalID = 0;
    private String name;
    private int score;
    private int id;

    /**
     * @return the player's name
     */
    public String getName() {
        return name;
    }
    /**
     * @param setName Takes in a String as a paramter, sets the player's name
     */
    public void setName(final String setName) {
        this.name = setName;
    }

    /**
     * @return this player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds one to players score
     */
    public void addScore() {
        score = score + 1;
    }

    /**
     * @return the current player's id
     */
    public int getID() {
        return id;
    }

    /**
     * @param setName the name for the new player
     *                Create a new player with the given name.
     *                Each player's score begins at zero, and each receives a monotonically increasing unique ID.
     */
    public Player(final String setName) {
        this.name = setName;
        this.score = 0;
        this.id = Player.globalID++;
    }

    /**
     * @param other Takes in the Player class object other
     *              Creates a copy of a player
     *              does not modify the state of the original player but displays their information
     */
    public Player(final Player other) {
        this.name = other.name;
        this.score = other.score;
        this.id = other.id;
    }

    /**
     * @return the string name
     */
    public String toString() {
        return name.substring(0, 3);
    }
}
