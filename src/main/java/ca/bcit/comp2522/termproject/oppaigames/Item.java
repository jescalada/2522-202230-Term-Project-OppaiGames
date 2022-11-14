package ca.bcit.comp2522.termproject.oppaigames;

public class Item {
    /**
     * Default name for an item.
     */
    public static final String DEFAULT_NAME = "item";

    private final String name;
    private final String description;
    private final double price;
    private final int quantity;

    /**
     * Constructs an item with the specified information.
     * @param name a string
     * @param description a string
     * @param price a double
     * @param quantity an integer
     */
    public Item(String name, String description, double price, int quantity) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            this.name = DEFAULT_NAME;
        }
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets the name of an item.
     * @return a string representing the name of an item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of an item.
     * @return a string representing the description of an item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the price of an item.
     * @return a string representing the description of an item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the price of an item.
     * @return a string representing the description of an item
     */
    public int getQuantity() {
        return quantity;
    }
}
