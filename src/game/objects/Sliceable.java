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
        if (type== SliceableType.APPLE)
            return new Fruit(Fruit.getAppleImages(), 10);
        else if (type== SliceableType.ORANGE)
            return new Fruit(Fruit.getOrangeImages(), 10);
        else if (type== SliceableType.KIWI)
            return new Fruit(Fruit.getKiwiImages(), 10);
        else if (type== SliceableType.DANGEROUS_BOMB)
            return new DangerousBomb();
        else if (type== SliceableType.FATAL_BOMB)
            return new FatalBomb();
        else if (type==SliceableType.POM)
            return new Fruit(Fruit.getPomImages(), 10);
        else if (type==SliceableType.PEAR)
            return new Fruit(Fruit.getPearImages(), 10);
        else if (type==SliceableType.LEMON)
            return new Fruit(Fruit.getLemonImages(), 10);
        else if (type==SliceableType.SPECIAL_1)
            return new Fruit(Fruit.getSpecial1Images(), 30);
        else if (type==SliceableType.SPECIAL_2)
            return new Fruit(Fruit.getSpecial2Images(), 30);



        return null;
    }
}
