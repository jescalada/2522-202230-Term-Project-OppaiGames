package ca.bcit.comp2522.termproject.oppaigames;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private static GameController game_controller = null;
    private final Player player;
    private final Shop shop;
    private final List<GatheringPoint> gatheringPoints;
    private final List<Recipe> recipes;
    private final List<Item> items;

    private GameController() {
        player = new Player("Pepe");
        shop = new Shop();
        gatheringPoints = loadGatheringPoints();
        recipes = loadRecipes();
        items = loadItems();
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

    /**
     * Starts the game.
     */
    public void startGame() {

    }
}
