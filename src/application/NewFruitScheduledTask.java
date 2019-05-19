package application;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class NewFruitScheduledTask extends TimerTask {
	static Timer timer = new Timer();
	private AnchorPane pn_fruits;
	
	NewFruitScheduledTask(AnchorPane pn_fruits){
		this.pn_fruits = pn_fruits;
	}
	
    public void run() {
    	int delay = (new Random().nextInt(5)) * 300;
    	//TODO add stop condition
    	timer.schedule(new NewFruitScheduledTask(pn_fruits), delay);
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
    	
    	ProjectileAnimation animation = new ProjectileAnimation(image);
    	animation.play();

    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
		    	pn_fruits.getChildren().add(image);
			}
        });
    
     }
}
