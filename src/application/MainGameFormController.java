package application;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MainGameFormController {	
	@FXML
	private Line line;
	
    @FXML
    private AnchorPane pn_main;
    
    @FXML
    Path path;
    
    @FXML 
    ImageView image;
    
    private Thread thread;
    
    private static MainGameFormController instance = null;
    public static MainGameFormController getInstance() {
        return instance;
    }
    
    public static void setInstance(MainGameFormController instance1) {
        instance = instance1;
    }
    
    @FXML
	public void initialize() {
    	 fruitPathAnimation();
    	 startThread();
    }
    
    //TODO: takes imageView as Parameter , randomize animation
    private void fruitPathAnimation(){
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
    	pathTransition.play();
    }
    
    private void startThread() {
    	thread = new intersectionThread();
        thread.setDaemon(true);
        thread.start();
    }
    
    @FXML
    void onMousePressed(MouseEvent event) {
    	path.toBack();
    	path.getElements().clear();
    	path.toFront();
    	image.setImage(new Image("/orange.png",true));
    	path.setStrokeWidth(3);
    	path.setStroke(Color.ANTIQUEWHITE);
    	    	
    	path.getElements()
            .add(new MoveTo(event.getSceneX(), event.getSceneY()));
    }

    @FXML
    void onMouseDragged(MouseEvent event) {
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

}
