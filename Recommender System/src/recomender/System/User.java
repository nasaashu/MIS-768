package RecommenderSystem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	private String userId;
	private String userName;
	private String emailId;
	private String userPwd;
	private Date dateOfBirth;
	
	public User(String id) {
		this.userId=id;
	}
	
	public User() {}
	
	public User(String id, String name, String email, String pwd,Date dob)
	{
		this.userId=id;
		this.userName=name;
		this.emailId=email;
		this.userPwd=pwd;
		this.dateOfBirth=dob;
	}

	public User(String id, String name, String email, String pwd, String dob) {
		this.userId=id;
		this.userName=name;
		this.emailId=email;
		this.userPwd=pwd;
		this.dateOfBirth=convertToDate(dob);
	}
	
	public User(String name, String email, String pwd, String dob) {
		this.userName=name;
		this.emailId=email;
		this.userPwd=pwd;
		this.dateOfBirth=convertToDate(dob);
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int calcAge(Date dateOfBirth)
	{
		int years;
		int months;
		
		//create calendar object for birth day
	      Calendar birthDay = Calendar.getInstance();
	      birthDay.setTimeInMillis(dateOfBirth.getTime());
	      
		 //create calendar object for current day
	      long currentTime = System.currentTimeMillis();
	      Calendar now = Calendar.getInstance();
	      now.setTimeInMillis(currentTime);
	      
	    //Get difference between years
	      years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
	      int currMonth = now.get(Calendar.MONTH) + 1;
	      int birthMonth = birthDay.get(Calendar.MONTH) + 1;
		
	      //Get difference between months
	      months = currMonth - birthMonth;
	      
	      if(months<0)
	      {
	    	  years--;
	      }
	      
		return years;
	}
	
	public boolean checkValidName(String name)
	{
		boolean isValid=false;
		name=name.toLowerCase();
		for(int i=0;i<name.length();i++)
		{
			if(Character.isLetter(name.charAt(i)))
			{
				isValid=true;
			}
			else
			{
				isValid=false;
				break;
			}
		}
		return isValid;
	}
	
	public Date convertToDate(String dob)
	{
		Date date = null;
		 try {

			 DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
			 date = (Date) parser.parse(dob);
			//dateConverted=new SimpleDateFormat("yyyy-mm-dd").parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 return date;

	}
	
	public String dateToString(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String formatedDate = cal.get(Calendar.YEAR)+"-"+ String.format("%02d",cal.get(Calendar.MONTH) + 1) + 
				"-" +String.format("%02d",cal.get(Calendar.DATE));
		return formatedDate;
	}
	
	public boolean checkValidDate(String dob)
	{
		DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		try {
            LocalDate.parse(dob, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
	}
	
	public boolean checkValidEmail(String email)
	{
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(matcher.matches())
			return true;
		else
			return false;
	}
	
	public int calculatePasswordStrength(String password){
        
        //total score of password
        int iPasswordScore = 0;
        
        if( password.length() < 8 )
            return 0;
        else if( password.length() >= 10 )
            iPasswordScore += 2;
        else 
            iPasswordScore += 1;
        
        //if it contains one digit, add 2 to total score
        if( password.matches("(?=.*[0-9]).*") )
            iPasswordScore += 2;
        
        //if it contains one lower case letter, add 2 to total score
        if( password.matches("(?=.*[a-z]).*") )
            iPasswordScore += 2;
        
        //if it contains one upper case letter, add 2 to total score
        if( password.matches("(?=.*[A-Z]).*") )
            iPasswordScore += 2;    
        
        //if it contains one special character, add 2 to total score
        if( password.matches("(?=.*[~!@#$%^&*()_-]).*") )
            iPasswordScore += 2;
        
        return iPasswordScore;
	}
	
	public String pwdStrengthIndicator(String pwd)
	{
		if(calculatePasswordStrength(pwd)<6)
			return "Weak";
		else if(calculatePasswordStrength(pwd)<8)
			return "Moderate";
		else
			return "Strong";
	}
	
	public String toString()
	{
		String str=null;
		str="User Id : "+userId+";\tUser Name : "+userName+";\tPassword : "+userPwd+";\tEmail : "+emailId+";\tDate of birth : "+dateToString(dateOfBirth);
		return str;
	}
}
