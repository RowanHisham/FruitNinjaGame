package application;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IntersectionThread extends Thread{
		
        @Override
        public void run(){
            while(!Thread.interrupted()){               
                try {
                    Thread.sleep(40);
                    for(Node node: MainGameFormController.getInstance().pn_fruits.getChildren()) {
                    	if(node.getClass() == ImageView.class) {
                    		if (isIntersecting((ImageView)node) ) {                        

                    			Platform.runLater(new Runnable() {
                    				@Override
                    				public void run() {
                    					((ImageView) node).setImage(new Image("/orangeSplit.png",true));
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
        
        boolean isIntersecting(ImageView image) {
        	return MainGameFormController.getInstance().path.getBoundsInParent().intersects(image.getBoundsInParent());
        }    
        
}
