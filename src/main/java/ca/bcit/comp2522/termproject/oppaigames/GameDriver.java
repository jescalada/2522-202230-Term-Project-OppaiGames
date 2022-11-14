package ca.bcit.comp2522.termproject.oppaigames;

public class GameDriver {
    private static GameController game;

    /**
     * Drives the game controller to start the game.
     */
    public static void main(String[] args) {
        GameController game = GameController.getInstance();
        game.startGame();
    }
}
