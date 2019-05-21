package game.objects;

import commands.EndGameCommand;
import commands.LoseLifeCommand;
import game.Game;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.util.ArrayList;
import java.util.List;

public class FatalBomb extends Sliceable {
    private static final List<Image> IMAGES = new ArrayList<>();
    private static final List<Media> SOUNDS = new ArrayList<>();
    static {
        IMAGES.add(new Image("/bombFatal"));
       SOUNDS.add(new Media("/bomb.mp3"));
    }

    FatalBomb() {
        super(IMAGES, SOUNDS);
    }

    @Override
    public void slice() {
        Game.getCurrentGame().getController().executeCommand(new EndGameCommand());
    }
}
