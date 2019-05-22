package application;

import commands.Controller;
import commands.SliceCommand;
import game.objects.Sliceable;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Random;

public class IntersectionThread extends Thread{
	private AnchorPane pn_fruits, pn_main;
	private Path path;
	IntersectionThread(AnchorPane pn_fruits,AnchorPane pn_main, Path path){
		this.pn_fruits = pn_fruits;
		this.pn_main = pn_main;
		this.path = path;
	}

	@Override
	public void run(){
		while(!Thread.interrupted()){
			try {
				Thread.sleep(150);
				if(pn_fruits.getChildren() == null)
					return;
				for(Node node: pn_fruits.getChildren()) {
					if (node instanceof ImageView
							&& node.getProperties().get("isSliced") == null
							&& isIntersecting(node) ) {
						node.getProperties().put("isSliced", true);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
                                Sliceable sliceable = (Sliceable) node.getUserData();
                                Controller.executeCommand(new SliceCommand(sliceable));
                                ((ImageView)node).setImage(sliceable.getImages().get(1));
								ImageView splash = new ImageView(sliceable.getImages().get(2));
								MediaPlayer mediaPlayer = new MediaPlayer(sliceable.randomSound());
								mediaPlayer.setOnReady(new Runnable() {
									@Override
									public void run() {
										mediaPlayer.stop();
										mediaPlayer.play();
									}
								});
								mediaPlayer.stop();
								mediaPlayer.play();

								Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
								splash.setX(boundsInScene.getMinX());
								splash.setY(boundsInScene.getMinY());
								splash.setOpacity(0.5);
								splash.setRotate(new Random().nextInt(360));

								FadeTransition ft = new FadeTransition(Duration.millis(3000), splash);
								ft.setToValue(0);
								ft.play();

								pn_main.getChildren().add(splash);
								pn_fruits.toFront();
							}
						});

					}
				}
			} catch (InterruptedException ex) {
				System.out.println("Interrupted");
				Thread.currentThread().interrupt();
			}
		}
	}

	boolean isIntersecting(Node node) {
		return path.getBoundsInParent().intersects(node.getBoundsInParent());
	}

}
