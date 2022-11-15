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
        List<Recipe> recipes = new ArrayList<>();
        Scanner sc;
        try {
            // pass the path to the file as a parameter
            File file = new File(RECIPE_FILE_LOCATION);
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                // Parse recipes line by line
                String[] recipeParameters = sc.nextLine().split(" \\| ");
                String recipeName = recipeParameters[0];
                String recipeDescription = recipeParameters[1];
                Map<Item, Integer> ingredients = new HashMap<>();
                String[] ingredientList = recipeParameters[2].split(", ");
                for (String ingredient : ingredientList) {
                    String[] ingredientQuantity = ingredient.split("x ");
                    try {
                        Item item = findItemByName(ingredientQuantity[1]);
                        ingredients.put(item, Integer.valueOf(ingredientQuantity[0]));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Map<Item, Integer> products = new HashMap<>();
                String[] productList = recipeParameters[3].split(", ");
                for (String product : productList) {
                    String[] productQuantity = product.split("x ");
                    try {
                        Item item = findItemByName(productQuantity[1]);
                        ingredients.put(item, Integer.valueOf(productQuantity[0]));
                        products.put(item, Integer.valueOf(productQuantity[0]));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Recipe recipe = new Recipe(recipeName, recipeDescription, ingredients, products);
                recipes.add(recipe);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    /**
     * Loads all items.
     * @return
     */
    public List<Item> loadItems() {
        List<Item> items = new ArrayList<>();
        Scanner sc;
        try {
            // pass the path to the file as a parameter
            File file = new File(ITEM_FILE_LOCATION);
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                // Parse recipes line by line
                String[] itemParameters = sc.nextLine().split(" \\| ");
                String itemName = itemParameters[0];
                String itemDescription = itemParameters[1];
                int itemValue = Integer.parseInt(itemParameters[2]);
                Item item = new Item(itemName, itemDescription, itemValue);
                items.add(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
