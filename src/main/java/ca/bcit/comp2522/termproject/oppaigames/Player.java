package ca.bcit.comp2522.termproject.oppaigames;

import java.util.Map;

public class Player {
    private final String name;
    private double money = 1000.00;
    private final Map<Item, Integer> inventory = null;

    /**
     * Constructs a player with the name.
     * @param name a string
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gets the name of a player.
     * @return a string representing the name of a player
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the money a player has.
     * @return a string representing the name of a player
     */
    public double getMoney() {
        return money;
    }

    /**
     * Gets the quantity of a specific item the player has.
     * @param item an item that player may or may not have
     * @return an integer representing the quantity of a specific item
     */
    public int getQuantity(Item item) {
        return inventory.get(item);
    }

    /**
     * Adds the quantity of a specific item the player has.
     * @param item an item to be added to player's inventory
     * @param quantity an integer representing the amount of item to be added
     */
    public void addItem(Item item, int quantity) {
        inventory.put(item, inventory.get(item) + quantity);
    }

    /**
     * Deducts the quantity of a specific item the player has.
     * @param item an item to be deducted from player's inventory
     * @param quantity an integer representing the amount of item to be deducted
     */
    public void deductItem(Item item, int quantity) {
        inventory.put(item, inventory.get(item) - quantity);
    }

    /**
     * Adds the amount of money a specific item the player has.
     * @param moneyObtained a double representing the amount of money obtained by a player
     */
    public void addMoney(double moneyObtained) {
        money = money + moneyObtained;
    }

    /**
     * Deducts the amount of money a specific item the player has.
     * @param moneySpent a double representing the amount of money spent by a player
     */
    public void deductMoney(double moneySpent) {
        money = money - moneySpent;
    }
}
