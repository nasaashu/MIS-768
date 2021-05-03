package RecommenderSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MovieDetailController {

    @FXML
    private Label movieNameLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label movieActorLabel;

    @FXML
    private Label releaseDateLabel;

    @FXML
    private TextArea commentsTextArea;

    @FXML
    private RadioButton zeroRadioBtn;

    @FXML
    private ToggleGroup raringGrp;

    @FXML
    private RadioButton oneRadioBtn;

    @FXML
    private RadioButton twoRadioBtn;

    @FXML
    private RadioButton threeRadioBtn;

    @FXML
    private RadioButton fourRadioBtn;

    @FXML
    private RadioButton fiveRadioBtn;

    @FXML
    private Label avgRatingLabel;

    @FXML
    private Button saveButton;
    
    @FXML
    private Button menuButton;
    
    User user;
    
    Movie movie;
    
    MovieRating rating;
    
    int oldRating=-1;

    public void initData(User u, Movie m)
    {
    	user=u;
    	movie=m;
    	System.out.println(m.getId());
    	MovieRatingDAO mrDAO;
    	mrDAO=new MovieRatingDAOImpl();
    	rating=mrDAO.getMovieRatingByUserID(u.getUserId(), m.getId());
    	initFields();
    	
    	if(rating!=null)
    	{
        	oldRating=rating.getRating();
    		switch(rating.getRating()) {
    		case 0: zeroRadioBtn.setSelected(true);
    				break;
    		case 1: oneRadioBtn.setSelected(true);
					break;
    		case 2: twoRadioBtn.setSelected(true);
					break;
    		case 3: threeRadioBtn.setSelected(true);
					break;
    		case 4: fourRadioBtn.setSelected(true);
					break;
    		case 5: fiveRadioBtn.setSelected(true);
					break;		
    		}
    		
    		commentsTextArea.setText(rating.getComment());
    	}
    	else
    		rating=new MovieRating();
    	
    	movieNameLabel.setText(m.getMovieName());
    	descriptionLabel.setText(m.getDescription());
    	movieActorLabel.setText(m.getActors());
    	releaseDateLabel.setText(m.getYear());
    }
    
    public void initFields()
    {
    	zeroRadioBtn.setSelected(false);
    	oneRadioBtn.setSelected(false);
    	twoRadioBtn.setSelected(false);
    	threeRadioBtn.setSelected(false);
    	fourRadioBtn.setSelected(false);
    	fiveRadioBtn.setSelected(false);
    	commentsTextArea.setText("");
    }
    
    public void saveButton_OnClick(ActionEvent e) throws IOException {
    	int movieRating;
   		
    	if(oneRadioBtn.isSelected())
    		movieRating=1;
    	else if(twoRadioBtn.isSelected())
    		movieRating=2;
    	else if(threeRadioBtn.isSelected())
    		movieRating=3;
    	else if(fourRadioBtn.isSelected())
    		movieRating=4;
    	else if(fiveRadioBtn.isSelected())
    		movieRating=5;
    	else
    		movieRating=0;
    	
    	System.out.println("rating :"+movieRating);
    	rating.setRating(movieRating);
    	rating.setUser(user);
    	rating.setId(movie.getId());
    	rating.setComment(commentsTextArea.getText());
    	
    	MovieRatingDAO mrDAO;
    	mrDAO=new MovieRatingDAOImpl();
    	boolean flag = mrDAO.saveMovieRating(oldRating,rating);
    	if(flag==true)
    	{
        	oldRating=rating.getRating();
    		//successful 
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Ratings saved");
	    	alert.setContentText("Movie rating saved successfully");
	    	alert.showAndWait();
	    	updateTrainingFile();
    	}
    	else
    	{
    		//failed
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Update failed");
	    	alert.setContentText("Updated failed.Please try again!");
	    	alert.showAndWait();
    	}
    }
    
    public void menuButton_OnClick(ActionEvent e) throws IOException {
    	// Instantiate the FXMLLoader object for loading the UI 
    	FXMLLoader loader=new FXMLLoader();
    	
    	// specify the file location for the FXML file for the next window
    	loader.setLocation(getClass().getResource("MovieList.fxml"));
		Parent parent=loader.load();
		Scene scene = new Scene(parent);

    	// access the controller class for the next window via the FXML loader
    	MovieListController controller=loader.getController();
    	controller.initData(user);
    	System.out.println("movie detail menu click");
    	// get the current stage, using the ActionEvent object
    	Stage stage= (Stage)((Node)e.getSource()).getScene().getWindow();
    	// change the title
    	stage.setTitle("Login");
    	// set the new scene to the stage
    	stage.setScene(scene);
    	// show the stage
    	stage.show();
    }
    
    //save updated ratings into the training file
    public void updateTrainingFile() throws IOException
    {
    	String fileName="user_ratings.csv";
    	//open the file
		FileWriter fw=new FileWriter(fileName,false);
		PrintWriter outputFile=new PrintWriter(fw);
		
		MovieRatingDAO mrDAO;
    	mrDAO=new MovieRatingDAOImpl();
    	ArrayList<MovieRating> ratingList = mrDAO.getMovieRatingsAll();
		
		for(int i=0;i<ratingList.size();i++)
		{
			outputFile.println(ratingList.get(i).getUser().getUserId()+","+ratingList.get(i).getId()+","+ratingList.get(i).getRating());
		}
		
		outputFile.close();
		System.out.print("The data has been saved in the file.");
    }
    
}

