package ca.bcit.comp2522.termproject.oppaigames;

import java.util.Map;

public class Recipe {
    private final String name;
    private final String description;
    private final Map<Item, Integer> ingredients;
    private final Map<Item, Integer> result;

    /**
     * Constructs a recipe with the specified information.
     * @param name a string
     * @param description a string
     * @param ingredients a map of ingredient items and numbers needed to make a product
     * @param result a map of product items and their numbers
     */
    public Recipe(String name, String description, Map<Item, Integer> ingredients, Map<Item, Integer> result) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.result = result;
    }

    /**
     * Gets the name of a recipe.
     * @return a string representing the name of a recipe
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of a recipe.
     * @return a string representing the description of a recipe
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the ingredients of a recipe.
     * @return a map representing the ingredient items and numbers needed to make a product
     */
    public Map<Item, Integer> getIngredients() {
        return ingredients;
    }

    /**
     * Gets the product result of a recipe.
     * @return a map representing the product item and numbers
     */
    public Map<Item, Integer> getResult() {
        return result;
    }
}
