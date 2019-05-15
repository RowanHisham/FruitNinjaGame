package commands;

import game.strategies.LivesStrategy;

public class LoseLifeCommand implements Command {
    private static LivesStrategy livesStrategy;

    @Override
    public void execute() {
        //TODO decrement lives
    }
}
