package application;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class MainGameFormController {	
	@FXML
	private Line line;
	
	 @FXML
	 JFXButton btn_mainmenu;
	
    @FXML
    AnchorPane pn_main, pn_fruits;
    
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
    	image.setVisible(false);
    	 startThread(); 
         new NewFruitScheduledTask().run();
    }
   
    private void startThread() {
    	thread = new IntersectionThread();
        thread.setDaemon(true);
        thread.start();
    }
    
    @FXML
    void onButtonAction(ActionEvent event) {
    	if(event.getSource() == btn_mainmenu) {
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.close();
    	}
    }
    
    @FXML
    void onMousePressed(MouseEvent event) {
    	path.toBack();
    	path.getElements().clear();
    	path.toFront();
    	image.setImage(new Image("/orange.png",true));   	    	
    	path.getElements()
            .add(new MoveTo(event.getSceneX(), event.getSceneY()));
    }

    @FXML
    void onMouseDragged(MouseEvent event) {
    	if(path.getElements().size() > 20) {
    		
    		MoveTo temp2 = null;
    		if( path.getElements().get(10).getClass() == MoveTo.class){
        		System.out.println("Moveto");

        	 temp2 = (MoveTo) path.getElements().get(10);
    		}else if(path.getElements().get(10).getClass() == LineTo.class) {
        		System.out.println("Line");

    			LineTo temp = (LineTo) path.getElements().get(10);
    			temp2 = new MoveTo(temp.getX(),temp.getY());
    		}
    		
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

}
