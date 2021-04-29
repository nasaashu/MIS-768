package RecommenderSystem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MovieRecommenderController implements Initializable {

    @FXML
    private VBox vbox;

    @FXML
    private Button menuButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		int userID=5;//for testing
		
		Recommender recommender = new Recommender();
	    ArrayList<Integer> movieIDList=new ArrayList<Integer>();
	    movieIDList=recommender.getRecommededMovies(userID);
	    
	    MovieDAO mDAO = new MovieDAOImpl();
	    ArrayList<Movie> movie_list=mDAO.getMovieDetailsByIDs(movieIDList); 
	    //ArrayList<Movie> movie_list=mDAO.getMovieListAll();
	    System.out.println(movie_list.size());
	    for(int i=0;i<movie_list.size();i++)
	    {
	    	
	    	Label label=new Label(movie_list.get(i).getMovieName());
	    	vbox.getChildren().add(label);
	    	System.out.println(movie_list.get(i).getMovieName());
	    }
		
	}
}
