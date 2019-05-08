package application;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class IntersectionThread extends Thread{
		
        @Override
        public void run(){
            while(!Thread.interrupted()){               
                try {
                	//TODO: decrease sleep duration
                    Thread.sleep(200);
                    if(MainGameFormController.getInstance().pn_fruits.getChildren() == null)
                    	return;
                    
                    for(Node node: MainGameFormController.getInstance().pn_fruits.getChildren()) {
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
                    				
                    					Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
                    					splash.setX(boundsInScene.getMinX());
                    					splash.setY(boundsInScene.getMinY());
                    					splash.setOpacity(0.5);
                    					splash.setRotate(new Random().nextInt(360));
                    					
                    					FadeTransition ft = new FadeTransition(Duration.millis(3000), splash);
                    			        ft.setToValue(0);
                    			        ft.play();
                    			        
                    			        MainGameFormController.getInstance().pn_main.getChildren().add(splash);
                    					MainGameFormController.getInstance().pn_fruits.toFront();
                    					MainGameFormController.getInstance().path.toFront();
                    					MainGameFormController.getInstance().btn_mainmenu.toFront();
                    					
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
        	return MainGameFormController.getInstance().path.getBoundsInParent().intersects(image.getBoundsInParent());
        }    
        
}
