package RecommenderSystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button viewRecommendationButton;

    @FXML
    private Button rateMovieButton;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button signOutButton;
    
    User user;
    
    public void initData(User u)
    {
    	user=u;
    }

    @FXML
    void editProfileButton_OnClick(ActionEvent event) throws IOException {

    	// Instantiate the FXMLLoader object for loading the UI 
    	FXMLLoader loader=new FXMLLoader();
    	
    	// specify the file location for the FXML file for the next window
    	loader.setLocation(getClass().getResource("UpdateDetails.fxml"));
		Parent parent=loader.load();
		Scene scene = new Scene(parent);
    	
    	// access the controller class for the next window via the FXML loader
    	UpdateDetailsController controller=loader.getController();
    	// call the method in the controller class for the next window
    	// so that the string can be passed
    	controller.initialize(user);

    	// get the current stage, using the ActionEvent object
    	Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
    	// change the title
    	stage.setTitle("Menu");
    	// set the new scene to the stage
    	stage.setScene(scene);
    	// show the stage
    	stage.show();
    }

    @FXML
    void rateMovieButton_OnClick(ActionEvent event) {

    }

    @FXML
    void signOutButton_OnClick(ActionEvent event) throws IOException {
    	// Instantiate the FXMLLoader object for loading the UI 
    	FXMLLoader loader=new FXMLLoader();
    	
    	// specify the file location for the FXML file for the next window
    	loader.setLocation(getClass().getResource("Login.fxml"));
		Parent parent=loader.load();
		Scene scene = new Scene(parent);
    	
    	// access the controller class for the next window via the FXML loader
    	LoginController controller=loader.getController();

    	// get the current stage, using the ActionEvent object
    	Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
    	// change the title
    	stage.setTitle("Login");
    	// set the new scene to the stage
    	stage.setScene(scene);
    	// show the stage
    	stage.show();
    }

    @FXML
    void viewRecommendationButton_OnClick(ActionEvent event) throws IOException {
    	// Instantiate the FXMLLoader object for loading the UI 
    	FXMLLoader loader=new FXMLLoader();
    	
    	// specify the file location for the FXML file for the next window
    	loader.setLocation(getClass().getResource("MovieRecommendation.fxml"));
		Parent parent=loader.load();
		Scene scene = new Scene(parent);
    	
    	// access the controller class for the next window via the FXML loader
    	MovieRecommenderController controller=loader.getController();
    	controller.initialize(user);

    	// get the current stage, using the ActionEvent object
    	Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
    	// change the title
    	stage.setTitle("Login");
    	// set the new scene to the stage
    	stage.setScene(scene);
    	// show the stage
    	stage.show();
    }

}


