package game.objects;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.util.ArrayList;
import java.util.List;

public class Fruit extends Sliceable {
    private static final List<Image> APPLE_IMAGES = new ArrayList<>();
    private static final List<Image> ORANGE_IMAGES = new ArrayList<>();
    private static final List<Image> KIWI_IMAGES = new ArrayList<>();
    private static final List<Media> APPLE_SOUNDS = new ArrayList<>();
    private static final List<Media> ORANGE_SOUNDS = new ArrayList<>();
    private static final List<Media> KIWI_SOUNDS = new ArrayList<>();
    static {
        //TODO load fruit stuff
    }

    private int fruitScore;

    Fruit(List<Image> images, List<Media> sounds, int fruitScore) {
        super(images, sounds);
        this.fruitScore = fruitScore;
    }

    @Override
    public void slice() {

    }

    static List<Image> getAppleImages() {
        return APPLE_IMAGES;
    }
    static List<Image> getOrangeImages() {
        return ORANGE_IMAGES;
    }
    static List<Image> getKiwiImages() {
        return KIWI_IMAGES;
    }
    static List<Media> getAppleSounds() {
        return APPLE_SOUNDS;
    }
    static List<Media> getOrangeSounds() {
        return ORANGE_SOUNDS;
    }
    static List<Media> getKiwiSounds() {
        return KIWI_SOUNDS;
    }
}
