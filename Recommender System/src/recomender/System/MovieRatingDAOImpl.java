package RecommenderSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MovieRatingDAOImpl implements MovieRatingDAO {

	private MovieRating rating=new MovieRating(-1);
	@Override
	public MovieRating getMovieRatingByUserID(String userID,int movieID) {
		//rating.setRating(-1);
		System.out.println("get rating:"+rating.getRating());
		try {

			Connection conn = DBUtil.getDBConnection();
			Statement stmt = conn.createStatement(
	        		ResultSet.TYPE_SCROLL_INSENSITIVE,
	        		ResultSet.CONCUR_READ_ONLY);
			
			String sql = "select r.MovieID,f.title,f.description,f.release_year,"
					+"GROUP_CONCAT(concat(a.first_name,' ',a.last_name)),r.Ratings,r.MovieComment "
					+"from Ratings r "
					+"left join film f "
					+"on f.film_id=r.MovieID "
					+"left join film_actor fa "
					+"on f.film_id=fa.film_id "
					+"left JOIN actor a "
					+ "on a.actor_id=fa.actor_id "
					+ "where r.UserID= "+userID
					+" and r.MovieID = "+movieID
					+ " group by r.MovieID ;";
			
			//Execute the query.
			System.out.println(sql);
	        ResultSet result = stmt.executeQuery(sql);
	        if(result.next())
	        {
	        	rating=new MovieRating();
	        	System.out.println("SQL : "+Integer.parseInt(result.getString(6)));
		        rating.setRating(Integer.parseInt(result.getString(6)));
		        rating.setComment(result.getString(7));
	        }
	        stmt.close();
	        DBUtil.closeDBConnection(conn);
	        
			} 
		catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}
		return rating;
	}

	public boolean saveMovieRating(int oldRating,MovieRating mRating) {
		boolean flag=false;
		System.out.println("OUT rating:"+oldRating);
		if(oldRating==-1)
		{
			rating=mRating;
			System.out.println("IN rating:"+oldRating);
			try {
				System.out.println("IN rating null");
				Connection conn = DBUtil.getDBConnection();
				Statement stmt = conn.createStatement(
		        		ResultSet.TYPE_SCROLL_INSENSITIVE,
		        		ResultSet.CONCUR_READ_ONLY);
				
				String sql = "INSERT INTO Ratings(UserID,MovieID,Ratings,MovieComment) VALUES ("
						+mRating.getUser().getUserId()+","
						+mRating.getId()+","
						+mRating.getRating()+", '"
						+mRating.getComment()
						+"');";
				
				//Execute the query.
				System.out.println(sql);
		        int row = stmt.executeUpdate(sql);
		        if(row==1)
		        	flag=true;
		        else
		        	flag=false;
		        stmt.close();
		        DBUtil.closeDBConnection(conn);
				} 
			catch (Exception ex) {
					System.out.println("ERROR: " + ex.getMessage());
				}
		}
		else
		{
			try {

				Connection conn = DBUtil.getDBConnection();
				Statement stmt = conn.createStatement(
		        		ResultSet.TYPE_SCROLL_INSENSITIVE,
		        		ResultSet.CONCUR_READ_ONLY);
				
				String sql = "UPDATE Ratings SET Ratings = "+mRating.getRating()+",MovieComment = '"
						+mRating.getComment()
						+"' WHERE userID= " +mRating.getUser().getUserId()
						+ " and MovieID = "+mRating.getId();
				
				//Execute the query.
				System.out.println(sql);
		        int row = stmt.executeUpdate(sql);
		        System.out.println("row : "+row);
		        if(row==1)
		        	flag=true;
		        else
		        	flag=false;
		        stmt.close();
		        DBUtil.closeDBConnection(conn);
				} 
			catch (Exception ex) {
					System.out.println("ERROR: " + ex.getMessage());
				}
		}
		
		return flag;
	}

	@Override
	public ArrayList<MovieRating> getMovieRatingsAll() {
		ArrayList<MovieRating> ratingsList=new ArrayList<MovieRating>();
		try {

			Connection conn = DBUtil.getDBConnection();
			Statement stmt = conn.createStatement(
	        		ResultSet.TYPE_SCROLL_INSENSITIVE,
	        		ResultSet.CONCUR_READ_ONLY);
			
			String sql = "SELECT UserID,MovieID,ratings FROM Ratings ORDER BY UserID,MovieID";
			
			//Execute the query.
			System.out.println(sql);
	        ResultSet result = stmt.executeQuery(sql);
	        while(result.next())
	        {
	        	rating=new MovieRating();
	        	rating.setUser(new User(result.getString(1)));
	        	rating.setId(Integer.parseInt(result.getString(2)));
		        rating.setRating(Integer.parseInt(result.getString(3)));
		        ratingsList.add(rating);
	        }
	        stmt.close();
	        DBUtil.closeDBConnection(conn);
	        
			} 
		catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}
		return ratingsList;
	}

}
