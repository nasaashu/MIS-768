package RecommenderSystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	/**
	 * This method establishes the DB connection
	 * @return a database connection
	 */
	public static Connection getDBConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER_NAME, DBConstants.PASSWORD);
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		return conn;
		
	}

	/**
	 * This method closes the DB connection
	 * @param the connection to be closed
	 */
	public static void closeDBConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}
		}
	}

	
}
