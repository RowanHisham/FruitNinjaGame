package application;

import commands.Controller;
import commands.SliceCommand;
import commands.UpdateScoreCommand;
import game.Game;
import game.objects.Sliceable;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.Random;

public class IntersectionThread extends Thread{
	private AnchorPane pn_fruits, pn_main;
	private Path path;
	private LocalTime comboTime = LocalTime.now();
	private boolean firstComboSlice = true;
	private int combo = 0;
	
	
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
		if(path.getBoundsInParent().intersects(node.getBoundsInParent())){
			if(firstComboSlice) {
				comboTime =  LocalTime.now();
				combo++;
				firstComboSlice = false;
			}else if(comboTime.plusNanos(300000000).isAfter(LocalTime.now()) ){
				combo++;
				updateCombo(node);			
				System.out.println("COMBO " + combo);
			}else {
				firstComboSlice = true;
				combo = 0;	
			}
			return true;
		}

		return false;
	}
	
	void updateCombo(Node node) {
		if(combo > 1) {
			int temp = combo;
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
			Game.getCurrentGame().addScore(combo*5);
			Controller.executeCommand(new UpdateScoreCommand());
			Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
			Label comboLabel = new Label();
			comboLabel.setText(temp + " FRUIT\n COMBO!");
			comboLabel.setFont(new Font("Gang of Three",30));
			comboLabel.setStyle("-fx-text-fill: #ffce36");
			comboLabel.setLayoutX(boundsInScene.getMinX());
			comboLabel.setLayoutY(boundsInScene.getMinY());
			FadeTransition ft = new FadeTransition(Duration.millis(4000), comboLabel);
			ft.setToValue(0);
			ft.play();
			pn_main.getChildren().add(comboLabel);
			System.out.println("COMBO TIME'S UP");
				}
			});
			
		}	}

}
