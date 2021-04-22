/**
 * @author: Nirupama Mohan
 * Purpose : testing individual functions
 * How to Use : Uncomment blocks by Right click on the line->Source->Remove Block Comments
 */

package RecommenderSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class TestConnection {
	public static void main(String[] args) {
		
		
		//test for SQL connection
		
		/*
		 * final String DB_URL="jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3406585";
		 * final String USER_NAME="sql3406585"; final String PASSWORD="mA7zkufyN5";
		 * 
		 * try { Connection conn=DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
		 * System.out.println("DB connection established"); //System.out.print("in");
		 * //create statement object Statement stmt=conn.createStatement();
		 * 
		 * //write SQL statement String sql="SELECT * FROM Users";
		 * 
		 * //execute ResultSet result=stmt.executeQuery(sql);
		 * 
		 * while(result.next()) {
		 * System.out.print("User Name:"+result.getString("userName"));
		 * System.out.println("\temail:"+result.getString("email")); }
		 * 
		 * conn.close(); System.out.println("DB connection closed"); } catch(Exception
		 * e) { System.out.print("ERROR:"+e.getMessage()); }
		 */
		
		//test for getting movie details by movie name
		/*
		 * MovieDAO mDAO; mDAO=new MovieDAOImpl();
		 * System.out.print(mDAO.getMovieDetailsByName("ACADEMY DINOSAUR"));
		 */
		
		//test for getting all movies
		/*
		 * MovieDAO mDAO; mDAO=new MovieDAOImpl(); ArrayList<Movie>
		 * movie_list=mDAO.getMovieListAll(); for(int i=0;i<movie_list.size();i++)
		 * System.out.println(movie_list.get(i).getMovieName());
		 * System.out.println("Size:"+String.valueOf(movie_list.size()));
		 */
		
		//test insert user
		/*
		 * UserDAO uDAO; uDAO=new UserDAOImpl();//(String name, String email, String pwd,Date dob) 
		 * User user=new User("Sona","hiphop_mis@gmail.com","sona_hip400$","1995-10-21");
		 * System.out.print(uDAO.insertUserDetails(user));
		 */
		
		//test update user
		/*
		 * UserDAO uDAO; uDAO=new UserDAOImpl();//(String name, String email, String pwd,Date dob) 
		 * User user=new User();
		 * user=uDAO.getUserDetailsByUsername("Maya"); user.setUserPwd("ohMyGod44@");
		 * System.out.print(uDAO.updateUserDetails(user));
		 */
		
		//test delete user
		/*
		 * UserDAO uDAO; uDAO=new UserDAOImpl(); User user=new User();
		 * user=uDAO.getUserDetailsByUsername("Seb");
		 * System.out.print(uDAO.deleteUserDetails(user));
		 */
	}
}