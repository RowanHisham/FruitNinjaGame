package application;

import java.io.IOException;
import java.time.LocalTime;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class MainGameFormController {	
	@FXML
	private Line line;
	
	 @FXML
	 JFXButton btn_mainmenu, btn_mainmenu1;
	
    @FXML
    AnchorPane pn_main, pn_fruits, pn_gameOver;
    
    @FXML
    Path path;
    
    @FXML 
    ImageView img_lives;
    
    @FXML
    Label lbl_score,lbl_highScore, lbl_time, lbl_gameOverScore, lbl_gameOverBestScore;
    
    private Thread thread;
    
    private LocalTime swordSoundTime = LocalTime.now();  
    
    @FXML
	public void initialize() {
    	updateLives(3);
    	pn_gameOver.setVisible(false);
    	 startThread(); 
         new NewFruitScheduledTask(pn_fruits).run();
    }
   
    private void startThread() {
    	thread = new IntersectionThread(pn_fruits,pn_main, path);
        thread.setDaemon(true);
        thread.start();
    }
    
    @FXML
    void onButtonAction(ActionEvent event) throws IOException {
    	if(event.getSource() == btn_mainmenu || event.getSource() == btn_mainmenu1) {
				Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainMenuForm.fxml"));
				Scene MainFormScene = new Scene(root);
	    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    			window.setScene(MainFormScene);
    			window.show();
    	}
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

    	if(!thread.isAlive()) {
    		startThread();  		
    	}
    }
    
    @FXML
    void onMouseReleased(MouseEvent event) {
    	path.toBack();
    	path.getElements().clear();
    	path.toFront();
    	thread.interrupt();
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
    
    void gameOver() {
    	pn_gameOver.toFront();
    	pn_gameOver.setVisible(true);
    }
    
    void updateLives(int live) {
    	if(live>3 || live<0)
    		return;
    	
    	img_lives.setImage(new Image("/lives"+live+".png",true));
    }
}
