package ca.bcit.comp2522.termproject.oppaigames;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GameController {

    private static GameController game_controller = null;
    private final Player player;
    private final Shop shop;
    private final List<GatheringPoint> gatheringPoints;
    private final List<Recipe> recipes;
    private final List<Item> items;

    private static final String RECIPE_FILE_LOCATION = "src/main/resources/ca/bcit/comp2522/termproject/oppaigames/recipes.txt";
    private static final String ITEM_FILE_LOCATION = "src/main/resources/ca/bcit/comp2522/termproject/oppaigames/items.txt";

    private GameController() {
        player = new Player("Pepe");
        shop = new Shop();
        items = loadItems();
        recipes = loadRecipes();
        gatheringPoints = loadGatheringPoints();
    }

    /**
     * Gets an instance of the GameController class using the singleton pattern.
     * @return a game controller
     */
    public static GameController getInstance()
    {
        if (game_controller == null) {
            game_controller = new GameController();
        }
        return game_controller;
    }

    /**
     * Loads a couple of gathering points.
     * @return
     */
    public List<GatheringPoint> loadGatheringPoints() {

        return gatheringPoints;
    }

    /**
     * Loads all recipes.
     * @return
     */
    public List<Recipe> loadRecipes() {
        return recipes;
    }

    /**
     * Loads all items.
     * @return
     */
    public List<Item> loadItems() {
        return items;
    }

    /**
     * Handles player request to gather items at gathering points.
     */
    public void processGather() {

    }

    /**
     * Handles player request to buy.
     */
    public void processBuy() {

    }

    /**
     * Handles player request to sell.
     */
    public void processSell() {

    }

    /**
     * Handles player request to process recipes and produce desired items.
     */
    public void processRecipe() {

    }

    /**
     * Clears the shop and stocks it with some initial items.
     */
    public void restockShop() {

    }

    public List<Item> getItems() {
        return items;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Starts the game.
     */
    public void startGame() {

    }

    private Item findItemByName(String name) throws Exception {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new Exception(name + " does not exist!");
    }
}
