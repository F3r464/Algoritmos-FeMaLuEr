package Practica11.Interfaz.src;

public class GameManager {

    private static GameManager instance;

    private HashGamePanel gamePanel;

    private GameManager() {}

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startMenu() {
        new MenuPrincipal();
    }

    public void startGame() {
        gamePanel = new HashGamePanel();
    }

    public HashGamePanel getGamePanel() {
        return gamePanel;
    }
}