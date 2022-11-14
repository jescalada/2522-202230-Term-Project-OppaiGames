package ca.bcit.comp2522.termproject.oppaigames;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameDriver extends Application {
    private static GameController game;

    @Override
    public void start(Stage stage) {
        HBox root = new HBox();
        Scene scene = new Scene(root, 800, 800);

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

        root.getChildren().addAll(inventoryButton, mapButton, shopButton, craftButton);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        inventoryButton.setMinSize(100, 100);
        mapButton.setMinSize(100, 100);
        shopButton.setMinSize(100, 100);
        craftButton.setMinSize(100, 100);

        stage.setScene(scene);
        stage.setTitle("OppaiGames: The Awesome JavaFX Clicker Game");
        stage.show();
    }

    private void showInventory() {
        // Should load and display the inventory panel with corresponding info
    }
    private void showMap() {
        // Should load and display the inventory panel with corresponding info
    }
    private void showShop() {
        // Should load and display the inventory panel with corresponding info
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