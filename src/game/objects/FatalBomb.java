package game.objects;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.util.ArrayList;
import java.util.List;

public class FatalBomb extends Sliceable {
    private static final List<Image> IMAGES = new ArrayList<>();
    private static final List<Media> SOUNDS = new ArrayList<>();
    static {
        //TODO load fatal bomb stuff
    }

    FatalBomb() {
        super(IMAGES, SOUNDS);
    }

    @Override
    public void slice() {

    }
}
