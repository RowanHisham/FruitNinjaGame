package application;

import commands.Controller;
import commands.InitLivesCommand;
import commands.InitTimeCommand;
import game.Game;
import game.strategies.LivesStrategy;
import game.strategies.TimeStrategy;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.Random;

public class MainMenuFormController {

    @FXML
    private AnchorPane pn_main;

    @FXML
    private AnchorPane pn_fruits;

    @FXML
    private Path path;

    @FXML
    private AnchorPane pn_labels;

    @FXML
    private ImageView img_arcade;

    @FXML
    private ImageView img_classic;

    @FXML
    private ImageView img_quit;
    
    private LocalTime swordSoundTime = LocalTime.now();
    
    private boolean gameSelected = false;
    
    private MediaPlayer mediaPlayer;

    @FXML
	public void initialize() {
    	
    	mediaPlayer = new MediaPlayer( new Media(getClass().getResource("/mainTheme.mp3").toString()));
    	mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.setOnReady(new Runnable() {
			@Override
			public void run() {
				mediaPlayer.stop();
				mediaPlayer.play();
			}
		});
		
		
    	for(Node node : pn_labels.getChildren())
    		rotateImage((ImageView)node,20,-1);
    	
    	for(Node node : pn_fruits.getChildren())
    		if(node.getClass() == ImageView.class) {
    		rotateImage((ImageView)node,20,1);
    		}
	}
    
    void rotateImage(ImageView imageView, int delay, int direction) {
    	RotateTransition rt = new RotateTransition(Duration.seconds(delay), imageView);
    	rt.setByAngle(direction*360);
    	rt.setCycleCount(Animation.INDEFINITE);
    	rt.setInterpolator(Interpolator.LINEAR);
    	rt.play();
    }

    
    @FXML
    void onMousePressed(MouseEvent event) {
    	path.toBack();
    	path.getElements().clear();
    	path.toFront();
    	path.getElements()
            .add(new MoveTo(event.getSceneX(), event.getSceneY()));
    }

    @FXML
    void onMouseDragged(MouseEvent event) {
    	if(path.getElements().size() > 20) {
    		MoveTo temp2 = null;
    		if( path.getElements().get(10).getClass() == MoveTo.class){
        	 temp2 = (MoveTo) path.getElements().get(10);
    		}else if(path.getElements().get(10).getClass() == LineTo.class) {
    			LineTo temp = (LineTo) path.getElements().get(10);
    			temp2 = new MoveTo(temp.getX(),temp.getY());
    		}
    		playSwordSound();
        	path.getElements().clear();
    		path.getElements().add(temp2);	
    	}
    	
    	path.getElements()
        .add(new LineTo(event.getSceneX(), event.getSceneY()));

    	if(!gameSelected)
    		checkIntersection();
    }
    
    @FXML
    void onMouseReleased(MouseEvent event) {
    	path.toBack();
    	path.getElements().clear();
    	path.toFront();
    }
    
    void playSwordSound() {
    	if( swordSoundTime.plusSeconds(1).isBefore(LocalTime.now())) {
        MediaPlayer mediaPlayer = new MediaPlayer( new Media(getClass().getResource("/sword.mp3").toString()));
		mediaPlayer.setOnReady(new Runnable() {
			@Override
			public void run() {
				mediaPlayer.stop();
				mediaPlayer.play();
			}
		});
		mediaPlayer.stop();
		mediaPlayer.play();
		swordSoundTime = LocalTime.now();
    }
   }
    
    void checkIntersection() {
    	for(Node node: pn_fruits.getChildren()) {
        	if(node.getClass() == ImageView.class) {
        		if (isIntersecting((ImageView)node) ) {                        
        					
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
        					playSplashSound();
        					
        					Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
        					splash.setX(boundsInScene.getMinX());
        					splash.setY(boundsInScene.getMinY());
        					splash.setOpacity(0.5);
        					splash.setRotate(new Random().nextInt(360));
        					
        					FadeTransition ft = new FadeTransition(Duration.millis(3000), splash);
        					ft.setToValue(0);
        					ft.play();
        					animateFruit((ImageView)node);
        					pn_main.getChildren().add(splash);
        					pn_fruits.toFront();
        					gameSelected = true;
        					break;
        		}
        	}
    	}
    }
    
    void playSplashSound() {
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
    }
    
    void animateFruit(ImageView image) {
    	TranslateTransition trans = new TranslateTransition(Duration.seconds(2), image);
        trans.setFromY(image.getY());
        trans.setToY(pn_fruits.getHeight() - image.getY());
        
        trans.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	mediaPlayer.stop();
            	if(image.equals(img_classic)) {
            		Controller.setCommand(new InitLivesCommand((Stage)img_classic.getScene().getWindow()));
            		new Game(new LivesStrategy(), null);
            	}else if( image.equals(img_quit)) {
            		Stage window = (Stage)(img_quit.getScene().getWindow());
            		window.close();
            	}else if(image.equals(img_arcade)) {
					Controller.setCommand(new InitTimeCommand((Stage)img_arcade.getScene().getWindow()));
					new Game(new TimeStrategy(), null);
            	}
            }
        });
        trans.play();      
    }
    
    boolean isIntersecting(ImageView image) {
    	return path.getBoundsInParent().intersects(image.getBoundsInParent());
    }    
}