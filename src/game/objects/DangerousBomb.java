package game.objects;

import commands.LoseLifeCommand;
import game.Game;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.util.ArrayList;
import java.util.List;

public class DangerousBomb extends Sliceable {
    private static final List<Image> IMAGES = new ArrayList<>();
    private static final List<Media> SOUNDS = new ArrayList<>();
    static {
       // IMAGES.add(new Image("/"));
       // SOUNDS.add(new Media("/"));
    }

    DangerousBomb() {
        super(IMAGES, SOUNDS);
    }

    @Override
    public void slice() {
        //TODO on dangerous bomb slice
        Game.getCurrentGame().getController().executeCommand(new LoseLifeCommand());
    }
}
