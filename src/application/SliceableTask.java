package application;

import game.objects.Sliceable;
import game.objects.SliceableType;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SliceableTask extends TimerTask {
	static Timer timer = new Timer();
	private AnchorPane pn_fruits;
	
	SliceableTask(AnchorPane pn_fruits){
		this.pn_fruits = pn_fruits;
	}

	@Override
    public void run() {
    	int delay = (new Random().nextInt(5)) * 300;
    	//TODO add stop condition
    	timer.schedule(new SliceableTask(pn_fruits), delay);
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
    	image.setUserData(Sliceable.newSliceable(SliceableType.APPLE));
    	image.getProperties().put("projectileAnimation", animation);

    	Platform.runLater(() -> {
			pn_fruits.getChildren().add(image);
			animation.play();
        });
    
     }
}
