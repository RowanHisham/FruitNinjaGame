package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainGameForm.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGameForm.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			MainGameFormController.setInstance(loader.getController());
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
