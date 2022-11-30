package ca.bcit.comp2522.termproject.oppaigames;

import java.util.HashMap;
import java.util.Map;

public class Shop {
    private Map<Item, Integer> itemsInStock = new HashMap<>();
    private final double buyValueFactor = 2.0;
    private final double sellValueFactor = 0.5;

    /**
     * Deducts the number of items in stock when player buys it.
     * @param item that player wants to buy
     * @return the number of the item left in stock after buying
     */
    public int buyItem(Item item) {
//      itemsInStock.get(item) -= 1;
        return 0;
    }

    /**
     * Adds the number of items in stock when player sells it.
     * @param item that player wants to sell
     * @return the number of the item left in stock after selling
     */
    public int sellItem(Item item) {
//        return itemsInStock.get(item) + item.getQuantity();
        return 0;
    }

    /**
     * Checks if the shop has enough stock of a specific item.
     * @param item the item a player wants to buy or sell
     * @param quantity an integer representing the quantity of an item in stock
     * @return a boolean to show whether shop has enough stock of an item
     */
    public boolean checkStock(Item item, int quantity) {
        return itemsInStock.get(item) >= quantity;
    }

    /**
     * Adds an item to the stock of the shop.
     * @param item the item to be added
     * @param quantity the amount to be added
     */
    public void addItemToStock(Item item, int quantity) {
        if (itemsInStock.containsKey(item)) {
            itemsInStock.put(item, itemsInStock.get(item) + quantity);
        } else {
            itemsInStock.put(item, quantity);
        }
    }

    /**
     * Deduct an item from the stock of the shop.
     * @param item the item to be deducted
     * @param quantity the amount to be deducted
     */
    public void deductItemToStock(Item item, int quantity) {
        if (itemsInStock.get(item) == 1) {
            itemsInStock.remove(item);
        } else {
            itemsInStock.put(item, itemsInStock.get(item) - quantity);
        }
    }

    /**
     * Clears all items in the shop.
     */
    public void clearShopStock() {
        itemsInStock.clear();
    }

    public Map<Item, Integer> getItemsInStock() {
        return itemsInStock;
    }
}
