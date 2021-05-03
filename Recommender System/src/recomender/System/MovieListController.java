package RecommenderSystem;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MovieListController {

    @FXML
    private VBox vbox;

    @FXML
    private TextField movieNameTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Button viewAllButton;
    
    User user;
    
    public void initData(User u)
    {
    	//Initializing user
    	user=u;
    	
    	//get list of all movies
    	MovieDAO mDAO = new MovieDAOImpl();
	    ArrayList<Movie> movie_list=mDAO.getMovieListAll(); 
	    
	    //create hyperlinks with the movie name as the text for all the movies in the list
	    for(int i=0;i<movie_list.size();i++) 
	    {
	    	Hyperlink hypLink = new Hyperlink(movie_list.get(i).getMovieName());
    		//int id = movie_list.get(i).getId();
	    	Movie movie = movie_list.get(i);
	    	
    		//on click event for the hyperlink - should open movie details page for corresponding movie ID
	    	hypLink.setOnAction(new EventHandler<ActionEvent>() {
	    	    public void handle(ActionEvent e) {
	    	    	System.out.println("initt");
	    	    	//getMovieDetailsUI(e,movie);
	    	    	try {
	    	    		System.out.println();
	    	    	// Instantiate the FXMLLoader object for loading the UI 
	    	    	FXMLLoader loader=new FXMLLoader();
	    	    	
	    	    	// specify the file location for the FXML file for the next window
	    	    	loader.setLocation(getClass().getResource("MovieDetails.fxml"));
	    			Parent parent=loader.load();
	    			Scene scene = new Scene(parent);
	    	    	
	    	    	// access the controller class for the next window via the FXML loader
	    	    	MovieDetailController controller=loader.getController();
	    	    	controller.initData(user, movie);
	    	    	
	    	    	System.out.println("after");
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
	    	//add the control to the vbox dynamically
	    	vbox.getChildren().add(hypLink);
	    }
    }


    @FXML
    void searchButton_OnClick(ActionEvent event) {
    	
    	//check if movieName text field is not empty
    	if(!movieNameTextField.getText().equals(""))
    	{
    	MovieDAO mDAO = new MovieDAOImpl();
	    ArrayList<Movie> movieList=mDAO.getMovieDetailsByName(movieNameTextField.getText());
	    
	    //check if movielist based on the search text is empty
	    if(movieList.size()>0)
	    {
	    	//clear controls in vbox
	    	vbox.getChildren().clear();
	    	
	    	for(int i=0;i<movieList.size();i++)
	    	{
	    	Hyperlink hypLink = new Hyperlink(movieList.get(i).getMovieName());
    		//int id = movieList.get(i).getId();
	    	Movie movie = movieList.get(i);
	    	
    		//on click event for the hyperlink - should open movie details page for corresponding movie ID
    		hypLink.setOnAction(new EventHandler<ActionEvent>() {
	    	    public void handle(ActionEvent e) {
	    	    	System.out.println("seaarchh");
	    	    	getMovieDetailsUI(e,movie);
	    	    }
	    	    
	    	});
	    	vbox.getChildren().add(hypLink);
	    	}
	    }
	    else
	    {
	    	//clear controls in vbox
	    	vbox.getChildren().clear();
	    	Label label = new Label("Movie not found!");
	    	vbox.getChildren().add(label);
	    }
    	}
    		
    }

    @FXML
    void viewAllButton_OnClick(ActionEvent event) {
    	
    	//clear controls in vbox
    	vbox.getChildren().clear();
    	
    	//get list of all movies
    	MovieDAO mDAO = new MovieDAOImpl();
	    ArrayList<Movie> movie_list=mDAO.getMovieListAll(); 
	    
	    //create hyperlinks with the movie name as the text for all the movies in the list
	    for(int i=0;i<movie_list.size();i++) {
	    	Hyperlink hypLink = new Hyperlink(movie_list.get(i).getMovieName());
	    	//int id=movie_list.get(i).getId();
	    	Movie movie = movie_list.get(i);
	    	
	    	hypLink.setOnAction(new EventHandler<ActionEvent>() {

	    	    public void handle(ActionEvent e) {
	    	    	getMovieDetailsUI(e, movie);
	    	    }
	    	});
	    	//add the control to the vbox dynamically
	    	vbox.getChildren().add(hypLink);
	    }
    }
    
    public void menuButton_OnClick(ActionEvent e) throws IOException {
//change page to menu
    	
    	// Instantiate the FXMLLoader object for loading the UI 
    	FXMLLoader loader=new FXMLLoader();
    	
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
    	stage.setTitle("Login");
    	// set the new scene to the stage
    	stage.setScene(scene);
    	// show the stage
    	stage.show();
    }
    
    public void getMovieDetailsUI(ActionEvent e, Movie movie) {
    	try {
    		System.out.println();
    	// Instantiate the FXMLLoader object for loading the UI 
    	FXMLLoader loader=new FXMLLoader();
    	
    	// specify the file location for the FXML file for the next window
    	loader.setLocation(getClass().getResource("MovieDetails.fxml"));
		Parent parent=loader.load();
		Scene scene = new Scene(parent);
    	
    	// access the controller class for the next window via the FXML loader
    	MovieDetailController controller=loader.getController();
    	controller.initData(user, movie);
    	System.out.println("after");
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
}

