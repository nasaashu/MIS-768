package RecommenderSystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {

	   @FXML
	    private Label AlertLabel;

	    @FXML
	    private TextField userNameTextField;

	    @FXML
	    private TextField pwdTextField;

	    @FXML
	    private Button signInButton;
	    
	    @FXML
	    private Hyperlink createAccoutnHypLink;
	    
	    @FXML
	    private Label errorLabel;

	    User user;

	    public void init()
	    {
	    	userNameTextField.setText("");
	    	pwdTextField.setText("");
	    	errorLabel.setText("");
	    }
	    
	    public void clearTextFields()
	    {
	    	userNameTextField.setText("");
	    	pwdTextField.setText("");
	    }
	    
    public void SignUpButton_OnClick(ActionEvent e) throws IOException {
    	
    	String message=null;
    	boolean userExists=false;
    	String userName = userNameTextField.getText();
    	String password = pwdTextField.getText();
    	
    	UserDAO uDAO;
    	uDAO=new UserDAOImpl();
    	userExists= uDAO.findUserByUsername(userName);
    	
    	if(userExists) {
    		user=uDAO.getUserDetailsByUsername(userName);
    		System.out.println("username Entered : "+userNameTextField.getText()+" pwd entered : "+pwdTextField.getText()+
    				" username DB : "+user.getUserName()+" pwd DB : "+user.getUserPwd());
    		if(user.getUserPwd().equals(password))
    		{
    			//testing
//    			errorLabel.setStyle("-fx-text-fill: green;");
//    			message="Successfull login!";
//    			errorLabel.setText(message);
    			
    			//change page to menu
    	    	
    	    	// Instantiate the FXMLLoader object for loading the UI 
    	    	FXMLLoader loader=new FXMLLoader();
    	    	
    	    	// specify the file location for the FXML file for the next window
    	    	loader.setLocation(getClass().getResource("Menu.fxml"));
    			Parent parent=loader.load();
    			Scene scene = new Scene(parent);
    	    	
    	    	// access the controller class for the next window via the FXML loader
    	    	MenuController controller=loader.getController();
    	    	// call the method in the controller class for the next window
    	    	// so that the string can be passed
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
    		else
    		{
    			message="User name or password is incorrect.Please try again!";
    			errorLabel.setStyle("-fx-text-fill: red;");
    			errorLabel.setText(message);
    			
    			Alert alert = new Alert(AlertType.WARNING);
    	    	alert.setTitle("User Invalid");
    	    	alert.setContentText("User name or password is incorrect.Please try again!");

    	    	alert.showAndWait();
    			
    			clearTextFields();
    		}
    	}
    	else {
    		message="User does not exist!!";
    		errorLabel.setStyle("-fx-text-fill: red;");
    		errorLabel.setText(message);
    		
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("User Invalid");
	    	alert.setContentText("User does not exist!");

	    	alert.showAndWait();
    		
    		clearTextFields();
    	}
    	
    }
    
    public void createAccoutnHypLink_OnClick(ActionEvent e) throws IOException {
    	// Instantiate the FXMLLoader object for loading the UI 
    	FXMLLoader loader=new FXMLLoader();
    	
    	// specify the file location for the FXML file for the next window
    	loader.setLocation(getClass().getResource("SignUp.fxml"));
		Parent parent=loader.load();
		Scene scene = new Scene(parent);
    	
    	// access the controller class for the next window via the FXML loader
    	SignUpController controller=loader.getController();

    	// get the current stage, using the ActionEvent object
    	Stage stage= (Stage)((Node)e.getSource()).getScene().getWindow();
    	// change the title
    	stage.setTitle("SignUp");
    	// set the new scene to the stage
    	stage.setScene(scene);
    	// show the stage
    	stage.show();
    } 
    
}
