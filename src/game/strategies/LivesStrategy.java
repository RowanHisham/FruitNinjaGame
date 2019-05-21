package game.strategies;

import commands.Controller;
import commands.EndGameCommand;

public class LivesStrategy implements GameStrategy {
    private int lives = 3 ;
    private Controller game;
    private EndGameCommand endGame;
            
    @Override
    public void initialize() {
        LivesStrategy livesStrategy = new LivesStrategy();
        game = new Controller(livesStrategy);
        endGame = new EndGameCommand();
    }
    
    public void decrementLives() {
        if(lives == 0){
             game.executeCommand(endGame);
        }
        else{
            lives--;
        }
    }
    
    public void setLives(int lives) {
        this.lives = lives;
    }
}
