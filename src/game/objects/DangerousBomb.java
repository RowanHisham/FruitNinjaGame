package game.objects;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.util.ArrayList;
import java.util.List;

public class DangerousBomb extends Sliceable {
    private static final List<Image> IMAGES = new ArrayList<>();
    private static final List<Media> SOUNDS = new ArrayList<>();
    static {
        //TODO load dangerous bomb stuff
    }

    DangerousBomb() {
        super(IMAGES, SOUNDS);
    }

    @Override
    public void slice() {
        //TODO on dangerous bomb slice
        // This should execute a command that decrements lives
    }
}
