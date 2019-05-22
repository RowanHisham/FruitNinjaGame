package commands;

import application.MainGameFormController;
import game.Game;

import java.io.FileOutputStream;
import java.io.IOException;

public class EndGameCommand implements Command {
    @Override
    public void execute() {
        Game.getCurrentGame().getState().stop();
        MainGameFormController.getInstance().gameOver();
        int highScore = Game.getCurrentGame().getHighScore();
        String fileName = Game.getCurrentGame().getStrategy().toString() + "_high_score";
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            outputStream.write(highScore);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
