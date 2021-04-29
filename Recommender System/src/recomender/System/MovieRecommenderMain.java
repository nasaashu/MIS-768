package RecommenderSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MovieRecommenderMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		System.out.println("IN");
		try {
		//create FXML object
		FXMLLoader loader=new FXMLLoader();
		System.out.println("IN");
		//set location of the object
		loader.setLocation(getClass().getResource("MovieRecommendation.fxml"));
		System.out.println("IN");
		//create Parent
		Parent parent = loader.load();
		//create scene and assign
		Scene scene = new Scene(parent);
		//set scene to stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("Movie recommender login");
		System.out.println("IN");
		//show stage
		primaryStage.show();
		
		}
		catch(Exception e)
		{
			System.out.print("ERROR:"+e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
