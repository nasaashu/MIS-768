package RecommenderSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAOImpl implements UserDAO{

	
	public boolean findUserByUsername(String username) {
		
		boolean userFound=false;
		/*
		try {
			Connection conn = DBUtil.getDBConnection();
			Statement stmt = conn.createStatement(
	        		ResultSet.TYPE_SCROLL_INSENSITIVE,
	        		ResultSet.CONCUR_READ_ONLY);
			
			
			String sql = "SELECT userId, userName, email, userPwd, dateOfBirth from " + DBConstants.USER_TABLE_NAME+
					     " WHERE userName"+" = '"+username+"';";
			//Execute the query.
			System.out.println(sql);
	        ResultSet result = stmt.executeQuery(sql);
	        if(result.next())
	        	userFound=true;
	        stmt.close();
	        DBUtil.closeDBConnection(conn);
	
			} 
		catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}
			*/
		User user = getUserDetailsByUsername(username);
		if(user == null)
		{
			userFound=false;
		}
		else
			userFound=true;
		System.out.println("user found : "+userFound +"\n "+ user);
		return userFound;
	}
	
	public User getUserDetailsByUsername(String username) {
		User user = null;
		try {
			Connection conn = DBUtil.getDBConnection();
			Statement stmt = conn.createStatement(
	        		ResultSet.TYPE_SCROLL_INSENSITIVE,
	        		ResultSet.CONCUR_READ_ONLY);
			
			
			String sql = "SELECT userId, userName, email, userPwd, dateOfBirth from " + DBConstants.USER_TABLE_NAME+
					     " WHERE userName = '"+username+"';";
			//Execute the query.
			System.out.println(sql);
	        ResultSet result = stmt.executeQuery(sql);
	        if(result.next())
		        // create a new object and fill the field with the values from the result set.
		        user  = new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
		
	        stmt.close();
	        DBUtil.closeDBConnection(conn);
	
			} 
		catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}
			return user;
	}
	
	public boolean insertUserDetails(User user) {
		//int userId;
	
		boolean flag=false;
		try {
			Connection conn = DBUtil.getDBConnection();
			Statement stmt = conn.createStatement();

			//get the max id from DB and insert max+1 as userId 
			
			//String sql1 = "SELECT max(userId) from " + DBConstants.USER_TABLE_NAME+";";
			//ResultSet result = stmt.executeQuery(sql1);
			//userId=Integer.parseInt(result.getString(0));
			
			String sql = "INSERT INTO `Users` (`userId`, `userName`, `userPwd`, `dateOfBirth`, `email`) VALUES (NULL, '"+
					     user.getUserName()+"', '"+
					     user.getUserPwd()+"', '"+	
					     user.dateToString(user.getDateOfBirth())+"', '"+			     
					     user.getEmailId()+
					     "')";
			System.out.println(sql);
			//Execute the query.
			int rows = stmt.executeUpdate(sql);
			
			if (rows ==1)
			{
				flag=true;
				System.out.print("inserted");
			}			
            stmt.close();
            DBUtil.closeDBConnection(conn);
            
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return flag;
	}
	
	public boolean updateUserDetails(User user) {
		boolean flag=false;
		try {
			Connection conn = DBUtil.getDBConnection();
			Statement stmt = conn.createStatement();

			//get the max id from DB and insert max+1 as userId 
			
			//String sql1 = "SELECT max(userId) from " + DBConstants.USER_TABLE_NAME+";";
			//ResultSet result = stmt.executeQuery(sql1);
			//userId=Integer.parseInt(result.getString(0));
			
			String sql = "UPDATE " + DBConstants.USER_TABLE_NAME+
					     " SET "+"userName = '"+
					     user.getUserName()+"', "+
					     "email = '"+
					     user.getEmailId()+"', "+
					     "userPwd = '"+
					     user.getUserPwd()+"', "+
					     "dateOfBirth = '"+
					     user.dateToString(user.getDateOfBirth())+
					     "' WHERE userId="+user.getUserId();
			System.out.println(sql);
			//Execute the query.
			int rows = stmt.executeUpdate(sql);
			
			if (rows ==1)
			{
				flag=true;
				System.out.println("updated");
			}			
            stmt.close();
            DBUtil.closeDBConnection(conn);
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return flag;
	}

	public boolean deleteUserDetails(User user) {
		boolean flag=false;
		try {
			Connection conn = DBUtil.getDBConnection();
			Statement stmt = conn.createStatement();

			//get the max id from DB and insert max+1 as userId 
			
			//String sql1 = "SELECT max(userId) from " + DBConstants.USER_TABLE_NAME+";";
			//ResultSet result = stmt.executeQuery(sql1);
			//userId=Integer.parseInt(result.getString(0));
			
			String sql = "DELETE FROM Users WHERE userId = "+user.getUserId();
			System.out.println(sql);
			//Execute the query.
			int rows = stmt.executeUpdate(sql);
			
			if (rows ==1)
			{
				flag=true;
				System.out.print("deleted");
			}			
            stmt.close();
            DBUtil.closeDBConnection(conn);
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return flag;
	}

	
}
