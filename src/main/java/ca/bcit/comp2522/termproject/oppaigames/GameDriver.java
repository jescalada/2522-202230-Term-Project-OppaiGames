package ca.bcit.comp2522.termproject.oppaigames;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class GameDriver extends Application {
    private static GameController game;

    /**
     * Starts the JavaFX application with the game.
     * @param stage the JavaFX stage onto which to display
     */
    @Override
    public void start(Stage stage) {
        HBox center = new HBox();
        BorderPane root = new BorderPane();
        StackPane top = new StackPane();
        Scene scene = new Scene(root, 1200, 800);
        Text currencyLabel = new Text();
        currencyLabel.setText((int)game.getPlayerMoney() + "G");
        currencyLabel.setScaleX(3);
        currencyLabel.setScaleY(3);
        titleButtonsSetup(stage, center);
        center.setSpacing(10);
        center.setAlignment(Pos.CENTER);
        top.getChildren().addAll(currencyLabel);
        top.setPadding(new Insets(20));
        root.setCenter(center);
        root.setTop(top);
        stage.setScene(scene);
        stage.setTitle("OppaiGames: The Awesome JavaFX Clicker Game");
        stage.show();
    }

    /**
     * Sets up the buttons in the main game page.
     * @param stage the stage onto which to display
     * @param root the root panel to append the buttons
     */
    private void titleButtonsSetup(Stage stage, Pane root) {
        Button inventoryButton = new Button("_Inventory");
        Button mapButton = new Button("_Map");
        Button shopButton = new Button("_Shop");
        Button craftButton = new Button("_Craft");
        inventoryButton.setOnAction(event -> {
            showInventory(stage, game);
        });
        mapButton.setOnAction(event -> {
            showMap(stage, game);
        });
        shopButton.setOnAction(event -> {
            showShop(stage, game);
        });
        craftButton.setOnAction(event -> {
            showCraft(stage, game);
        });
        inventoryButton.setMinSize(100, 100);
        mapButton.setMinSize(100, 100);
        shopButton.setMinSize(100, 100);
        craftButton.setMinSize(100, 100);
        root.getChildren().addAll(inventoryButton, mapButton, shopButton, craftButton);
    }

    /**
     * Displays the inventory onto the main stage.
     * @param stage the JavaFX stage onto which to display
     * @param game the game instance containing all the model data
     */
    private void showInventory(Stage stage, GameController game) {
        Map<Item, Integer> inventory = game.getPlayerInventory();

        BorderPane root = new BorderPane();
        TilePane center = new TilePane();
        StackPane top = new StackPane();
        StackPane bottom = new StackPane();

        Scene scene = new Scene(root, 1920, 1040);

        Text panelTitle = new Text();

        panelTitle.setText("Inventory");
        panelTitle.setScaleX(3);
        panelTitle.setScaleY(3);

        for (Item item : inventory.keySet()) {
            VBox box = new VBox();
            Text itemName = new Text("        " + item.getName() + "        ");
            Text itemDescription = new Text("               ");
            Text itemValue = new Text(item.getValue() + "G");
            Text itemQuantity = new Text("Qty: " + inventory.get(item));

            box.getChildren().addAll(itemName, itemDescription, itemValue, itemQuantity);
            box.setScaleX(1.3);
            box.setScaleY(1.3);
            BackgroundFill background_fill = new BackgroundFill(Color.PINK,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);
            box.setBackground(background);
            box.setPadding(new Insets(10));
            center.getChildren().add(box);
        }

        Button backButton = new Button("_Back");
        backButton.setOnAction(event -> {
            start(stage);
        });
        backButton.setMinSize(100,100);

        center.setPadding(new Insets(20));
        center.setAlignment(Pos.CENTER);

        top.getChildren().addAll(panelTitle);
        top.setPadding(new Insets(20));

        bottom.getChildren().add(backButton);

        root.setCenter(center);
        root.setTop(top);
        root.setBottom(bottom);

        stage.setScene(scene);
        stage.setTitle("OppaiGames: The Awesome JavaFX Clicker Game");
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Places a back button on a pane.
     * @param stage the stage in which we're placing objects
     * @param root the pane onto which to append the back button.
     */
    private void placeBackButton(Stage stage, Pane root) {
        Button backButton = new Button("_Back");
        backButton.setOnAction(event -> {
            start(stage);
        });
        backButton.setMinSize(100,100);
        root.getChildren().add(backButton);
    }

    /**
     * Sets the frontend using the values in the inventory.
     * @param inventory the inventory to print out
     * @param root the root Pane in which to append the items
     */
    private void setInventoryFrontend(Map<Item, Integer> inventory, Pane root) {
        for (Item item : inventory.keySet()) {
            VBox box = new VBox();
            Text itemName = new Text("        " + item.getName() + "        ");
            Text itemDescription = new Text("               ");
            Text itemValue = new Text(item.getValue() + "G");
            Text itemQuantity = new Text("Qty: " + inventory.get(item));

            box.getChildren().addAll(itemName, itemDescription, itemValue, itemQuantity);
            box.setScaleX(1.3);
            box.setScaleY(1.3);
            BackgroundFill background_fill = new BackgroundFill(Color.PINK,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);
            box.setBackground(background);
            box.setPadding(new Insets(10));
            root.getChildren().add(box);
        }
    }

    /**
     * Sets a title on a given Pane.
     * @param root the root Pane object to which to place the title
     * @param title a string representing the title to apply
     */
    private void setPanelTitle(Pane root, String title) {
        Text panelTitle = new Text();
        panelTitle.setText(title);
        panelTitle.setScaleX(3);
        panelTitle.setScaleY(3);
        root.getChildren().addAll(panelTitle);
        root.setPadding(new Insets(20));
    }

    /**
     * Displays the map onto the main stage.
     * @param stage the JavaFX stage onto which to display
     * @param game the game instance containing all the model data
     */
    private void showMap(Stage stage, GameController game) {
        List<GatheringPoint> gatheringPoints = game.loadGatheringPoints();

        BorderPane root = new BorderPane();
        TilePane center = new TilePane();
        StackPane top = new StackPane();
        StackPane bottom = new StackPane();
        Scene scene = new Scene(root, 1920, 1040);
        Text panelTitle = new Text();
        panelTitle.setText("Map");
        panelTitle.setScaleX(3);
        panelTitle.setScaleY(3);

        for (GatheringPoint point : gatheringPoints) {
            VBox box = new VBox();
            Text pointName = new Text(point.getName());
            Text pointDescription = new Text("                     ");
            Button gatherButton = new Button("_Gather");

            gatherButton.setOnAction(event -> {
                String gathered = game.processGather(point.getName());
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Gathering from " + point.getName() + "...");
                a.setContentText(gathered);
                a.show();
            });

            gatherButton.setMinSize(100, 50);

            box.getChildren().addAll(pointName, pointDescription, gatherButton);
            box.setScaleX(1);
            box.setScaleY(1);
            BackgroundFill background_fill = new BackgroundFill(Color.PALEGREEN,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);
            box.setBackground(background);
            box.setPadding(new Insets(10));
            center.getChildren().add(box);
        }

        Button backButton = new Button("_Back");
        backButton.setOnAction(event -> {
            start(stage);
        });
        backButton.setMinSize(50,50);

        center.setPadding(new Insets(20));
        center.setScaleX(1.5);
        center.setScaleY(1.5);
        center.setAlignment(Pos.CENTER);

        top.getChildren().addAll(panelTitle);
        top.setPadding(new Insets(20));

        bottom.getChildren().add(backButton);

        root.setCenter(center);
        root.setTop(top);
        root.setBottom(bottom);

        stage.setScene(scene);
        stage.setTitle("OppaiGames: The Awesome JavaFX Clicker Game");
        stage.setMaximized(true);
        stage.show();
    }

    private void showShop(Stage stage, GameController game) {
        Map<Item, Integer> itemsInStock = new HashMap<>();
        itemsInStock.put(new Item("Pepe Plushy", "A sad frog.", 10), 5);
        itemsInStock.put(new Item("Milk", "Yummy milk.", 50), 3);
        itemsInStock.put(new Item("Gold Bar", "A shiny 24K gold bar.", 1000), 8);

        Map<Item, Integer> inventory = new HashMap<>();
        inventory.put(new Item("Item 1", "First item", 10), 5);
        inventory.put(new Item("Item 2", "Second item", 15), 2);
        inventory.put(new Item("Item 3", "Third item", 7), 3);
        inventory.put(new Item("Item 4", "Fourth item", 20), 8);


        BorderPane root = new BorderPane();
        HBox center = new HBox();
        VBox leftPanel = new VBox();
        VBox rightPanel = new VBox();

        StackPane top = new StackPane();
        StackPane bottom = new StackPane();
        Scene scene = new Scene(root, 1920, 1040);

        Text panelTitle = new Text("Shop");
        Text leftPanelTitle = new Text("For Sale:");
        Text rightPanelTitle = new Text("Sell items:");
        leftPanel.getChildren().add(leftPanelTitle);
        rightPanel.getChildren().add(rightPanelTitle);
        panelTitle.setScaleX(3);
        panelTitle.setScaleY(3);
        leftPanelTitle.setScaleX(1.5);
        leftPanelTitle.setScaleY(1.5);
        rightPanelTitle.setScaleX(1.5);
        rightPanelTitle.setScaleY(1.5);

        for (Item item : itemsInStock.keySet()) {
            HBox box = new HBox();
            Text itemName = new Text(item.getName());
            Text itemDescription = new Text(item.getDescription());
            Button buyButton = new Button("_Buy");
            Text quantityInStock = new Text("In Stock: " + itemsInStock.get(item));

            box.getChildren().addAll(itemName, itemDescription, quantityInStock, buyButton);
            BackgroundFill background_fill = new BackgroundFill(Color.LIGHTGOLDENRODYELLOW,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);
            box.setBackground(background);
            box.setSpacing(20);
            box.setPadding(new Insets(10));
            leftPanel.getChildren().add(box);
        }

        for (Item item : inventory.keySet()) {
            HBox box = new HBox();
            Text itemName = new Text(item.getName());
            Text itemValue = new Text(item.getValue() + "G");
            Text itemQuantity = new Text("Qty: " + inventory.get(item));
            Button sellButton = new Button("_Sell");

            box.getChildren().addAll(itemName, itemValue, itemQuantity, sellButton);
            BackgroundFill background_fill = new BackgroundFill(Color.PINK,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);
            box.setBackground(background);
            box.setSpacing(20);
            box.setPadding(new Insets(10));
            rightPanel.getChildren().add(box);
        }

        Button backButton = new Button("_Back");
        backButton.setOnAction(event -> {
            start(stage);
        });
        backButton.setMinSize(50,50);

        leftPanel.setAlignment(Pos.CENTER);
        rightPanel.setAlignment(Pos.CENTER);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(10));

        top.getChildren().addAll(panelTitle);
        top.setPadding(new Insets(20));

        bottom.getChildren().add(backButton);

        center.getChildren().addAll(leftPanel, rightPanel);

        root.setCenter(center);
        root.setTop(top);
        root.setBottom(bottom);

        stage.setScene(scene);
        stage.setTitle("OppaiGames: The Awesome JavaFX Clicker Game");
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Displays the crafting page onto the main stage.
     * @param stage the JavaFX stage onto which to display
     * @param game the game instance containing all the model data
     */
    private void showCraft(Stage stage, GameController game) {
        // Should load and display the inventory panel with corresponding info
        List<Recipe> recipes = game.loadRecipes();

        BorderPane root = new BorderPane();
        VBox center = new VBox();
        StackPane top = new StackPane();
        StackPane bottom = new StackPane();

        Scene scene = new Scene(root, 1920, 1040);

        Text panelTitle = new Text();

        panelTitle.setText("Crafting");
        panelTitle.setScaleX(3);
        panelTitle.setScaleY(3);

        for (Recipe recipe : recipes) {
            HBox box = new HBox();
            String ingredients = "Needs: ";
            for (Item ingredient : recipe.getIngredients().keySet()) {
                ingredients += ingredient.getName() + " x" + recipe.getIngredients().get(ingredient) + " | ";
            }
            String products = "Makes: ";
            for (Item product : recipe.getProducts().keySet()) {
                products += product.getName() + " x" + recipe.getProducts().get(product) + " | ";
            }
            Text recipeName = new Text(recipe.getName());
            Text recipeDescription = new Text("");
            Text ingredientText = new Text(ingredients);
            Text resultText = new Text(products);
            Button craftButton = new Button("_Craft");

            craftButton.setOnAction(event -> {
                String crafted = game.processRecipe(recipe.getName());
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(recipe.getName() + "...");
                a.setContentText(crafted);
                a.show();
            });

            box.getChildren().addAll(recipeName, recipeDescription, ingredientText, resultText, craftButton);

            box.setScaleX(1.2);
            box.setScaleY(1.2);
            BackgroundFill background_fill = new BackgroundFill(Color.PINK,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(background_fill);
            box.setBackground(background);
            box.setPadding(new Insets(10));
            center.getChildren().add(box);
            box.setAlignment(Pos.CENTER);
        }

        Button backButton = new Button("_Back");
        backButton.setOnAction(event -> {
            start(stage);
        });
        backButton.setMinSize(100,100);

        center.setPadding(new Insets(40));
        center.setSpacing(20);
        center.setAlignment(Pos.CENTER);

        top.getChildren().addAll(panelTitle);
        top.setPadding(new Insets(20));

        bottom.getChildren().add(backButton);

        ScrollPane scrollPane = new ScrollPane(center);
        scrollPane.setFitToWidth(true);

        root.setCenter(center);
        root.setTop(top);
        root.setBottom(bottom);

        stage.setScene(scene);
        stage.setTitle("OppaiGames: The Awesome JavaFX Clicker Game");
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Drives the game controller to start the game.
     */
    public static void main(String[] args) {
        game = GameController.getInstance();
        game.startGame();
        launch(args);
    }
}
