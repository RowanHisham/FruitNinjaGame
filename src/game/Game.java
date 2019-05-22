package game;

import game.gamestate.GameState;
import game.strategies.GameStrategy;

import java.io.FileInputStream;
import java.io.IOException;

public class Game {
    private static Game currentGame;
    public static Game getCurrentGame() {
        return currentGame;
    }

    private GameState state;
    private int score;
    private int highScore;
    private GameStrategy strategy;


    public Game(GameStrategy strategy, GameState state) {
        this.strategy = strategy;
        this.state = state;
        this.score = 0;
        String fileName = strategy.toString() + "_high_score";
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            highScore = inputStream.read();
            inputStream.close();
        } catch (IOException e) {
            highScore = 0;
        }
        currentGame = this;
        strategy.initialize();
    }

    public void addScore(int toAdd) {
        score += toAdd;
        if(score > highScore)
            highScore = score;
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
    public int getHighScore() {
        return highScore;
    }
}
