package RecommenderSystem;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
	    private Label errorLabel;


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
	    
    public void SignUpButton_OnClick() {
    	
    	String message=null;
    	boolean userExists=false;
    	String userName = userNameTextField.getText();
    	String password = pwdTextField.getText();
    	
    	UserDAO uDAO;
    	uDAO=new UserDAOImpl();
    	userExists= uDAO.findUserByUsername(userName);
    	
    	if(userExists) {
    		User user=uDAO.getUserDetailsByUsername(userName);
    		System.out.println("username Entered : "+userNameTextField.getText()+" pwd entered : "+pwdTextField.getText()+
    				" username DB : "+user.getUserName()+" pwd DB : "+user.getUserPwd());
    		if(user.getUserPwd().equals(password))
    		{
    			errorLabel.setStyle("-fx-text-fill: green;");
    			message="Successfull login!";
    			errorLabel.setText(message);
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
    
}
