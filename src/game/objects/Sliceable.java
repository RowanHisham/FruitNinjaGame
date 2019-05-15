package game.objects;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.util.Collections;
import java.util.List;

public abstract class Sliceable {
    private List<Image> images;
    private List<Media> sounds;

    Sliceable(List<Image> images, List<Media> sounds) {
        this.images = images;
        this.sounds = sounds;
    }

    public final List<Image> getImages() {
        return Collections.unmodifiableList(images);
    }
    public final List<Media> getSounds() {
        return Collections.unmodifiableList(sounds);
    }

    public abstract void slice();

    public static Sliceable newSliceable(SliceableType type) {
        //TODO factory here
        return null;
    }
}
