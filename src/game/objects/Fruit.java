package game.objects;

import game.Game;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.util.ArrayList;
import java.util.List;

public class Fruit extends Sliceable {
    private static final List<Image> APPLE_IMAGES = new ArrayList<>();
    private static final List<Image> ORANGE_IMAGES = new ArrayList<>();
    private static final List<Image> KIWI_IMAGES = new ArrayList<>();
    private static final List<Image> POM_IMAGES = new ArrayList<>();
    private static final List<Media> SOUNDS = new ArrayList<>();
    private static final List<Media> APPLE_SOUNDS = new ArrayList<>();
    private static final List<Media> ORANGE_SOUNDS = new ArrayList<>();
    private static final List<Media> KIWI_SOUNDS = new ArrayList<>();
    private static final List<Media> POM_SOUNDS = new ArrayList<>();
    static {
         APPLE_IMAGES.add(new Image("/apple.png"));
        APPLE_IMAGES.add(new Image("/appleSplit.png"));
        APPLE_IMAGES.add(new Image("/colorSplash1.png"));
         ORANGE_IMAGES.add(new Image("/orange.png"));
        ORANGE_IMAGES.add(new Image("/orangeSplit.png"));
        ORANGE_IMAGES.add(new Image("/colorSplash2"));
        KIWI_IMAGES.add(new Image("/kiwi.png"));
        KIWI_IMAGES.add(new Image("/kiwiSplit.png"));
        KIWI_IMAGES.add(new Image("/colorSplash2.png"));
        POM_IMAGES.add(new Image("/pom.png"));
        POM_IMAGES.add(new Image("/pomSplit.png"));
        POM_IMAGES.add(new Image("/colorsplash1.png"));
        SOUNDS.add(new Media("/fruit0.mp3"));
        SOUNDS.add(new Media("/fruit1.mp3"));
        SOUNDS.add(new Media("/fruit2.mp3"));

    }


    private int fruitScore;

    Fruit(List<Image> images, int fruitScore) {
        super(images, SOUNDS);
        this.fruitScore = fruitScore;
    }

    @Override
    public void slice() {
        Game.getCurrentGame().addScore(fruitScore);
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
    static List<Image> getPOMImages() {
        return POM_IMAGES;
    }
    /*static List<Media> getAppleSounds() {
        return APPLE_SOUNDS;
    }
    static List<Media> getOrangeSounds() {
        return ORANGE_SOUNDS;
    }
    static List<Media> getKiwiSounds() {
        return KIWI_SOUNDS;
    }
    static List<Media> getPOMSounds() {
        return POM_SOUNDS;
    }*/
}
