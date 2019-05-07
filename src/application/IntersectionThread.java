package application;

import javafx.application.Platform;
import javafx.scene.image.Image;

public class IntersectionThread extends Thread{
		
        @Override
        public void run(){
        	System.out.println("herre");
            while(!Thread.interrupted()){               
                try {
                    Thread.sleep(40);
                    if (isIntersecting()) {                        
                        
                    	Platform.runLater(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								MainGameFormController.getInstance().image.setImage(new Image("/orangeSplit.png",true));
							}
                        });  
                        
                    }else {
                    	Platform.runLater(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								//image.setImage(new Image("/orange.png",true));
								System.out.println("Here");
							}
                        });
                    	
                    }
                } catch (InterruptedException ex) {
                	System.out.println("Interrupted");
                	Thread.currentThread().interrupt();
                }
            }
        }
        
        boolean isIntersecting() {
        	return MainGameFormController.getInstance().path.getBoundsInParent().intersects(MainGameFormController.getInstance().image.getBoundsInParent());
        }
        
        
}
