package game.strategies;


import commands.Controller;
import commands.EndGameCommand;

public class LivesStrategy implements GameStrategy {
    private int lives = 3 ;
            
    @Override
    public void initialize() {
        Controller.execute();
    }

    public void decrementLives() {
        if(lives == 0){
            Controller.executeCommand(new EndGameCommand());
        }
        else{
            lives--;
        }
    }
    public int getLives() {
        return lives;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public String toString() {
        return "Classic";
    }
}
