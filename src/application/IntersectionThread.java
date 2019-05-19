package application;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Path;
import javafx.util.Duration;

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
                	//TODO: decrease sleep duration
                    Thread.sleep(200);
                    if(pn_fruits.getChildren() == null)
                    	return;
                    
                    for(Node node: pn_fruits.getChildren()) {
                    	if(node.getClass() == ImageView.class) {
                    		if (isIntersecting((ImageView)node) ) {                        

                    			Platform.runLater(new Runnable() {
                    				@Override
                    				public void run() {
                    					
                    					int random = new Random().nextInt(3);
                    					ImageView splash;
                    					
                    					//TODO get images later from objects not randomized 
                    					if(random%2 == 0) {
                    					    ((ImageView) node).setImage(new Image("/pomSplit.png",true));
                    						splash = new ImageView(new Image("/colorsplash1.png",true));
                    						
                    					}else {
                    						((ImageView) node).setImage(new Image("/orangeSplit.png",true));
                        					 splash = new ImageView(new Image("/colorsplash2.png",true));
                    					}
                    				
                    					MediaPlayer mediaPlayer = new MediaPlayer( new Media(getClass().getResource("/fruit" + new Random().nextInt(3)+ ".mp3").toString()));
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
                    }
                } catch (InterruptedException ex) {
                	System.out.println("Interrupted");
                	Thread.currentThread().interrupt();
                }
            }
        }
        
        //TODO: check if node is already split and return false
        boolean isIntersecting(ImageView image) {
        	return path.getBoundsInParent().intersects(image.getBoundsInParent());
        }    
        
}
