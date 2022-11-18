package ca.bcit.comp2522.termproject.oppaigames;

import javafx.scene.effect.Light;

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
    private static final String GATHERINGPOINT_FILE_LOCATION = "src/main/resources/ca/bcit/comp2522/termproject/oppaigames/gatheringpoints.txt";

    private GameController() {
        player = new Player("Chris");
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
     * Starts the game.
     */
    public void startGame() {
        // todo add game loading logic
    }

    /**
     * Loads a couple of gathering points.
     * @return
     */
    public List<GatheringPoint> loadGatheringPoints() {
        List<GatheringPoint> gatheringPoints = new ArrayList<>();
        Scanner sc;
        try {
            // pass the path to the file as a parameter
            File file = new File(GATHERINGPOINT_FILE_LOCATION);
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                // Parse recipes line by line
                String[] pointParameters = sc.nextLine().split(" \\| ");
                String pointName = pointParameters[0];
                String pointDescription = pointParameters[1];
                Map<Item, Integer> quantities = new HashMap<>();
                String[] yieldQuantities = pointParameters[2].split(", ");
                for (String resource : yieldQuantities) {
                    String[] resourceQuantity = resource.split("x ");
                    try {
                        Item item = findItemByName(resourceQuantity[1]);
                        quantities.put(item, Integer.valueOf(resourceQuantity[0]));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Map<Item, Float> chances = new HashMap<>();
                String[] yieldChances = pointParameters[3].split(", ");
                for (String resource : yieldChances) {
                    String[] resourceChance = resource.split("% ");
                    try {
                        Item item = findItemByName(resourceChance[1]);
                        chances.put(item, Float.valueOf(resourceChance[0]) / 100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                GatheringPoint point = new GatheringPoint(pointName, pointDescription, quantities, chances);
                gatheringPoints.add(point);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    public String processGather(String gatheringPointName) {
        try {
            GatheringPoint point = findGatheringPointByName(gatheringPointName);
            Item item = point.getRandomItem();
            if (item == null) {
                return "You didn't gather anything!";
            } else {
                int quantityGathered = point.getYieldQuantitiesForItem(item);
                player.addItem(item, quantityGathered);
                return "You gathered " + quantityGathered + " " +  item.getName();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("You done goofed buddy");
        }
        return "This is not supposed to happen. Please contact Chris Thompson to resolve this matter.";
    }

    /**
     * Handles player request to process recipes and produce desired items.
     */
    public String processRecipe(String recipeName) {
        try {
            Recipe recipe = findRecipeByName(recipeName);
            Map<Item, Integer> requiredIngredients = recipe.getIngredients();
            boolean hasRequiredItems = true;
            String recipeResult = "";
            for (Map.Entry<Item, Integer> ingredient : requiredIngredients.entrySet()) {
                Item requiredItem = ingredient.getKey();
                Integer requiredNumber = ingredient.getValue();
                if (!player.getInventory().containsKey(requiredItem) || player.getInventory().get(requiredItem) <= requiredNumber) {
                    hasRequiredItems = false;
                    recipeResult += "You do not have enough ingredients to craft this product";
                }
            }
            if (hasRequiredItems) {
                for (Item ingredient: recipe.getIngredients().keySet()) {
                    player.deductItem(ingredient, recipe.getIngredients().get(ingredient));
                }
                for (Item product : recipe.getProducts().keySet()) {
                    int quantityCrafted = recipe.getProducts().get(product);
                    player.addItem(product, quantityCrafted);
                    recipeResult += "You crafted " + 1 + " " + product.getName();
                }
            }
            return recipeResult;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("You done goofed buddy");
        }
        return "This is not supposed to happen. Please contact Chris Thompson to resolve this matter.";
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
     * Clears the shop and stocks it with some initial items.
     */
    public void restockShop() {

    }

    public Map<Item, Integer> getPlayerInventory() {
        return player.getInventory();
    }

    private Item findItemByName(String name) throws Exception {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new Exception(name + " does not exist!");
    }

    private GatheringPoint findGatheringPointByName(String name) throws Exception {
        for (GatheringPoint point : gatheringPoints) {
            if (point.getName().equals(name)) {
                return point;
            }
        }
        throw new Exception(name + " does not exist!");
    }

    private Recipe findRecipeByName(String name) throws Exception {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }
        throw new Exception(name + " does not exist!");
    }
}
