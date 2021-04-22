package RecommenderSystem;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		User user=new User();
		/*
		String date;
		
		//check if date entered is valid
		System.out.print("Enter date:");
		date=scanner.next();
		

		if(user.checkValidDate(date))
			System.out.print("valid");
		else
			System.out.print("Invalid");

	    //calculate dob after converting to date 
		user.setDateOfBirth(user.convertToDate(date));
		System.out.print(user.calcAge());
		
		//check valid email
		System.out.print("Enter email:");
		String email=scanner.next();
		System.out.print(user.checkValidEmail(email));
		*/
		//check valid email
		System.out.print("Enter Password:");
		String pwd=scanner.next();
		System.out.print(user.pwdStrengthIndicator(pwd));
	}

}
