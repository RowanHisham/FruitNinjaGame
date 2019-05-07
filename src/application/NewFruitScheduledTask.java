package application;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class NewFruitScheduledTask extends TimerTask {
	static Timer timer = new Timer();
	
	NewFruitScheduledTask(){}
	
    public void run() {
    	int delay = (new Random().nextInt(5)) * 300;
        timer.schedule(new NewFruitScheduledTask(), delay);
    	fruitPathAnimation();
    }
   
    private void fruitPathAnimation(){ 
    	ImageView image = new ImageView();
    	int random = new Random().nextInt(30);
    	if(random%4 == 0) {
    		image.setImage(new Image("/kiwi.png",true));
    	}else if(random%4 == 1 ){
    		image.setImage(new Image("/orange.png",true));
    	}else if(random%4 == 2 ){
    		image.setImage(new Image("/apple.png",true));
    	}else if(random%4 == 3 ){
    		image.setImage(new Image("/pom.png",true));
    	}
    	
    	Path path = new Path();
    	path.getElements().add(new MoveTo(500,900));
    	path.getElements().add(new CubicCurveTo(200,800, 800, -600, 900, 900));
    	PathTransition pathTransition = new PathTransition();
    	pathTransition.setDuration(Duration.millis(3000));
    	pathTransition.setPath(path);
    	pathTransition.setNode(image);
    	pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    	pathTransition.setCycleCount(Timeline.INDEFINITE);
    	pathTransition.setAutoReverse(true);
    	
    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
		    	MainGameFormController.getInstance().pn_main.getChildren().add(image);
			}
        });
    	
    	pathTransition.play();
    }
}
