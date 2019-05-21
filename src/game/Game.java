package game;

import commands.Controller;
import game.strategies.GameStrategy;

public class Game {
    private static Game currentGame;
    public static Game getCurrentGame() {
        return currentGame;
    }

    private GameStrategy strategy;
    private GameState state;
    private int score;
    private Controller controller;

    public Game(GameStrategy strategy, GameState state) {
        this.strategy = strategy;
        this.state = state;
        this.score = 0;
        this.controller = new Controller(strategy);
        currentGame = this;
    }

    public void addScore(int toAdd) {
        score += toAdd;
    }

    public GameStrategy getStrategy() {
        return strategy;
    }
    public GameState getState() {
        return state;
    }
    public void setState(GameState state) {
        this.state = state;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public Controller getController() {
        return controller;
    }
}
