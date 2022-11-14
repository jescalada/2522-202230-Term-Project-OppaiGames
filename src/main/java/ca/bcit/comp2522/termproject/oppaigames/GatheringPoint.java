package ca.bcit.comp2522.termproject.oppaigames;

import java.util.Map;

public class GatheringPoint {
    private final String name;
    private final String description;
    private final Map<Item, Integer> itemYields;
    private final Map<Item, Float> yieldOdds;

    /**
     * Constructs a gathering point with the specified information.
     * @param name a string
     * @param description a string
     * @param itemYields a map of items and corresponding yields
     * @param yieldOdds a map of items and corresponding odds of getting an item
     */
    public GatheringPoint(String name, String description, Map<Item, Integer> itemYields, Map<Item, Float> yieldOdds) {
        this.name = name;
        this.description = description;
        this.itemYields = itemYields;
        this.yieldOdds = yieldOdds;
    }

    /**
     * Gathers item(s) at a specific gathering point.
     * @return an Item representing one or more items
     */
    public Item gather() {
        return new Item("Stefan", "human", 6, 3);
    }

    /**
     * Gets the name of a gathering point.
     * @return a string representing the name of a general point
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of a gathering point.
     * @return a string representing the description of a general point
     */
    public String getDescription() {
        return description;
    }
}
