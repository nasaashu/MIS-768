package RecommenderSystem;


import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class SignUpController {

	 	@FXML
	    private TextField userNameTextField;

	    @FXML
	    private TextField emailTextField;

	    @FXML
	    private DatePicker dobPicker;

	    @FXML
	    private Button submitButton;

	    @FXML
	    private PasswordField pwdTextField;

	    @FXML
	    private PasswordField confirmPwdTextField;

	    @FXML
	    private ProgressBar progressBar;

	    @FXML
	    private Button clearButton;

	    @FXML
	    private Label userNameLabel;

	    @FXML
	    private Label pwdLabel;
	    
	    @FXML
	    private Label emailLabel;
	    
	    @FXML
	    private Label dobLabel;

    
    public void init()
    {
    	userNameTextField.setText("");
    	emailTextField.setText("");
    	pwdTextField.setText("");
    	confirmPwdTextField.setText("");
    	progressBar.setTranslateX(0);
    	userNameLabel.setText("");
    	pwdLabel.setText("");
    	emailLabel.setText("");
    	dobLabel.setText("");

    }
    
    public void clearLabels()
    {
    	userNameLabel.setText("");
    	pwdLabel.setText("");
    	emailLabel.setText("");
    	dobLabel.setText("");
    }
    
    public void clearButton_OnCLick()
    {
    	userNameTextField.setText("");
    	emailTextField.setText("");
    	pwdTextField.setText("");
    	confirmPwdTextField.setText("");
    	progressBar.setTranslateX(0);
    	userNameLabel.setText("");
    }
    
    public void submitButton_OnClick(ActionEvent e) throws IOException
    {
    	String errorMessage;
    	boolean userExists;
    	String userName=userNameTextField.getText();
    	String password=pwdTextField.getText();
    	String confirmPassword=confirmPwdTextField.getText();
    	LocalDate dob = dobPicker.getValue();
    	String email=emailTextField.getText();
    	
    	//change LocalDate to Date for calling user class function to calculate age
    	// Getting system timezone
		ZoneId systemTimeZone = ZoneId.systemDefault();
		// converting LocalDateTime to ZonedDateTime with the system timezone
		ZonedDateTime zonedDateTime = dob.atStartOfDay(systemTimeZone);
		// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
		Date dobDate = Date.from(zonedDateTime.toInstant());
    	
    	User user = new User();
    	
    	UserDAO uDAO;
    	uDAO=new UserDAOImpl();
    	userExists= uDAO.findUserByUsername(userName);
    	System.out.println("userName : "+userName+" dob : "+dobDate+" pwd : "+password+" confirm pwd : "+confirmPassword+" Email : "+email);
    	//check if user Name entered already exists in DB
    	if(!userExists) 
    	{
    		clearLabels();
    		//System.out.println("in");
    		
    		int pwdStrength = user.calculatePasswordStrength(password);
    		System.out.println("Pwd Strength : "+pwdStrength);
    		//if(pwdStrength>=8)
    		//{
	    		if(password.equals(confirmPassword))
	    		{
	    			clearLabels();
	    			
	    			if(user.calcAge(dobDate)>18)
					{
						clearLabels();
						
		    			if(user.checkValidEmail(email))
		    			{
		    				//valid inputs
		    				user.setUserName(userName);
		    				user.setUserPwd(password);
		    				user.setDateOfBirth(dobDate);
		    				user.setEmailId(email);
		    				
		    				//save in DB
		    				boolean flag = uDAO.insertUserDetails(user);
		    				if(flag) 
		    				{
		    					//successful registration
			    				Alert alert = new Alert(AlertType.CONFIRMATION);
			        	    	alert.setTitle("Account created");
			        	    	alert.setContentText("Account has been created successfully");
			        	    	alert.showAndWait();
			        	    	
			        	    	//change page to menu
			        	    	// Instantiate the FXMLLoader object for loading the UI 
			        	    	FXMLLoader loader=new FXMLLoader();
			        	    	
			        	    	// specify the file location for the FXML file for the next window
			        	    	loader.setLocation(getClass().getResource("Login.fxml"));
			        			Parent parent=loader.load();
			        			Scene scene = new Scene(parent);
			        	    	
			        	    	// access the controller class for the next window via the FXML loader
			        	    	LoginController controller=loader.getController();
			        	    	// call the method in the controller class for the next window
			        	    	// so that the string can be passed
			        	    	//controller.initData(user);

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
		    					//registration failed
			    				Alert alert = new Alert(AlertType.WARNING);
			        	    	alert.setTitle("Account creation failed");
			        	    	alert.setContentText("Account creation failed.Please try again!");
			        	    	alert.showAndWait();
		    				}
		    			
		    			}
		    			else
		    			{
		    				errorMessage = "Invalid Email ID!!";
		    				emailLabel.setStyle("-fx-text-fill: red;");
		    				emailLabel.setText(errorMessage);
		    			}
					}
	    			else
					{
						errorMessage = "Users should be atleast 18 years of age!!";
	    				dobLabel.setStyle("-fx-text-fill: red;");
	    				dobLabel.setText(errorMessage);
					}
	    		}
	    		else
	    		{
	    			errorMessage = "Passwords don't match!!";
	    			pwdLabel.setStyle("-fx-text-fill: red;");
	    			pwdLabel.setText(errorMessage);
	    		}
    		/*}
    		else
    		{
    			progressBar.setProgress(pwdStrength/8);
    		}*/
    	}
    	else
    	{
    		errorMessage = "User name already exists!!";
			userNameLabel.setStyle("-fx-text-fill: red;");
			userNameLabel.setText(errorMessage);
    	}
    }

}

