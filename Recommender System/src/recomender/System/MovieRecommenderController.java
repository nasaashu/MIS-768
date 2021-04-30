package RecommenderSystem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MovieRecommenderController{

    @FXML
    private VBox vbox;

    @FXML
    private Button menuButton;
    
    private User user;
    
    public void initData(User u)
    {
    	user=u;//Integer.parseInt(user.getUserId());
    	
    	Recommender recommender = new Recommender();
	    ArrayList<Integer> movieIDList=new ArrayList<Integer>();
	    movieIDList=recommender.getRecommededMovies(Integer.parseInt(user.getUserId()));
	    
	    MovieDAO mDAO = new MovieDAOImpl();
	    ArrayList<Movie> movie_list=mDAO.getMovieDetailsByIDs(movieIDList); 
	    //ArrayList<Movie> movie_list=mDAO.getMovieListAll();
	    System.out.println(movie_list.size());
	    
	    for(int i=0;i<movie_list.size();i++)
	    {
	    	
	    	Hyperlink hypLink=new Hyperlink(movie_list.get(i).getMovieName());
	    	hypLink.setOnAction(new EventHandler<ActionEvent>() {

	    	    public void handle(ActionEvent e) {
	    	    	try {
	    	    	// Instantiate the FXMLLoader object for loading the UI 
	    	    	FXMLLoader loader=new FXMLLoader();
	    	    	
	    	    	// specify the file location for the FXML file for the next window
	    	    	loader.setLocation(getClass().getResource("MovieDetails.fxml"));
	    			Parent parent=loader.load();
	    			Scene scene = new Scene(parent);
	    	    	
	    	    	// access the controller class for the next window via the FXML loader
	    	    	SignUpController controller=loader.getController();

	    	    	// get the current stage, using the ActionEvent object
	    	    	Stage stage= (Stage)((Node)e.getSource()).getScene().getWindow();
	    	    	// change the title
	    	    	stage.setTitle("Movie Details");
	    	    	// set the new scene to the stage
	    	    	stage.setScene(scene);
	    	    	// show the stage
	    	    	stage.show();
	    	    	}
	    	    	catch(Exception ex) {}
	    	    }
	    	});
	    	vbox.getChildren().add(hypLink);
	    	System.out.println(movie_list.get(i).getMovieName());
	    }
	    
    }
    public void menuButton_OnClick(ActionEvent e) throws IOException {
    	// Instantiate the FXMLLoader object for loading the UI 
    	FXMLLoader loader=new FXMLLoader();
    	System.out.println("IN");
    	
    	// specify the file location for the FXML file for the next window
    	loader.setLocation(getClass().getResource("Menu.fxml"));
		Parent parent=loader.load();
		Scene scene = new Scene(parent);
    	
    	// access the controller class for the next window via the FXML loader
    	MenuController controller=loader.getController();
    	controller.initData(user);

    	// get the current stage, using the ActionEvent object
    	Stage stage= (Stage)((Node)e.getSource()).getScene().getWindow();
    	// change the title
    	stage.setTitle("Menu");
    	// set the new scene to the stage
    	stage.setScene(scene);
    	// show the stage
    	stage.show();
    }
		
	
}
