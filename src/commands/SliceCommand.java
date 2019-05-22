package commands;

import application.MainGameFormController;
import game.objects.Fruit;
import game.objects.Sliceable;
import javafx.animation.FadeTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Random;

public class SliceCommand implements Command {
    private ImageView image;

    public SliceCommand(ImageView image) {
        this.image = image;
    }
    @Override
    public void execute() {
        Sliceable toSlice = (Sliceable) image.getUserData();
        toSlice.slice();
        AnchorPane pn_fruits = MainGameFormController.getInstance().getFruitsPane();
        AnchorPane pn_main = MainGameFormController.getInstance().getMainPane();
        if(toSlice instanceof Fruit)
            Controller.executeCommand(new UpdateScoreCommand());
        if(toSlice instanceof Fruit) {
            image.setImage(toSlice.getImages().get(1));
            javafx.scene.image.ImageView splash = new javafx.scene.image.ImageView(toSlice.getImages().get(2));
            Bounds boundsInScene = image.localToScene(image.getBoundsInLocal());
            splash.setX(boundsInScene.getMinX());
            splash.setY(boundsInScene.getMinY());
            splash.setOpacity(0.5);
            splash.setRotate(new Random().nextInt(360));
            FadeTransition ft = new FadeTransition(Duration.millis(3000), splash);
            ft.setOnFinished((event) -> pn_main.getChildren().remove(splash));
            ft.setToValue(0);
            pn_main.getChildren().add(splash);
            ft.play();
            pn_fruits.toFront();
        }
        else {
            image.setVisible(false);
        }
        MediaPlayer mediaPlayer = new MediaPlayer(toSlice.randomSound());
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.stop();
            mediaPlayer.play();
        });
        mediaPlayer.stop();
        mediaPlayer.play();
    }
}
