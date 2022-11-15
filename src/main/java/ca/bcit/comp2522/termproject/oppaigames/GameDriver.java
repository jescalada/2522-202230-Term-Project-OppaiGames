package ca.bcit.comp2522.termproject.oppaigames;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class GameDriver extends Application {
    private static GameController game;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        HBox center = new HBox();
        StackPane top = new StackPane();

        Scene scene = new Scene(root, 800, 800);

        Text gameTitle = new Text();
        gameTitle.setText("The Best Clicker Game In The World");
        gameTitle.setScaleX(3);
        gameTitle.setScaleY(3);

        Button inventoryButton = new Button("_Inventory");
        Button mapButton = new Button("_Map");
        Button shopButton = new Button("_Shop");
        Button craftButton = new Button("_Craft");

        inventoryButton.setOnAction(event -> {
            showInventory();
        });
        mapButton.setOnAction(event -> {
            showMap();
        });
        shopButton.setOnAction(event -> {
            showShop();
        });
        mapButton.setOnAction(event -> {
            showCraft();
        });

        center.getChildren().addAll(inventoryButton, mapButton, shopButton, craftButton);
        center.setSpacing(10);
        center.setAlignment(Pos.CENTER);
        inventoryButton.setMinSize(100, 100);
        mapButton.setMinSize(100, 100);
        shopButton.setMinSize(100, 100);
        craftButton.setMinSize(100, 100);

        top.getChildren().add(gameTitle);
        top.setPadding(new Insets(20));

        root.setCenter(center);
        root.setTop(top);

        stage.setScene(scene);
        stage.setTitle("OppaiGames: The Awesome JavaFX Clicker Game");
        stage.show();
    }

    private void showInventory() {
        // Should load and display the inventory panel with corresponding info
    }
    private void showMap(Stage stage, GameController game) {
        List<GatheringPoint> gatheringPoints = new ArrayList<>();
        gatheringPoints.add(new GatheringPoint("Abandoned Mine", "An abandoned mine.", null, null));
        gatheringPoints.add(new GatheringPoint("Enchanted Forest", "A beautiful forest.", null, null));
        gatheringPoints.add(new GatheringPoint("Moo Moo Farm", "A farm with lots of cows.", null, null));

        BorderPane root = new BorderPane();
        TilePane center = new TilePane();
        StackPane top = new StackPane();
        StackPane bottom = new StackPane();
        Scene scene = new Scene(root, 800, 800);
        Text panelTitle = new Text();
        panelTitle.setText("Map");
        panelTitle.setScaleX(3);
        panelTitle.setScaleY(3);

        for (GatheringPoint point : gatheringPoints) {
            VBox box = new VBox();
            Text itemName = new Text(point.getName());
            Text itemDescription = new Text("                     ");
            Button gatherButton = new Button("_Gather");

            gatherButton.setMinSize(100, 50);

            box.getChildren().addAll(itemName, itemDescription, gatherButton);
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
        Scene scene = new Scene(root, 800, 800);

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
        stage.show();
    }
    private void showCraft() {
        // Should load and display the inventory panel with corresponding info
    }

    /**
     * Drives the game controller to start the game.
     */
    public static void main(String[] args) {
        GameController game = GameController.getInstance();
        game.startGame();
        launch(args);
    }
}
