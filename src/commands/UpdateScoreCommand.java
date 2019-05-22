package commands;

import application.MainGameFormController;
import game.Game;

public class UpdateScoreCommand implements Command {

    @Override
    public void execute() {
        Game currentGame = Game.getCurrentGame();
        MainGameFormController uiController = MainGameFormController.getInstance();
        uiController.updateScore(currentGame.getScore());
        uiController.updateHighScore(currentGame.getHighScore());
    }
}
