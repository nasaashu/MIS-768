package RecommenderSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MovieDAOImpl implements MovieDAO {

	public ArrayList<Movie> getMovieListAll(){
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		try {
			Movie movie;
			Connection conn = DBUtil.getDBConnection();
			Statement stmt = conn.createStatement(
	        		ResultSet.TYPE_SCROLL_INSENSITIVE,
	        		ResultSet.CONCUR_READ_ONLY);
			
			
			String sql = "select f.film_id,f.title as movie_name,f.description, f.release_year,fcc.name as genre, l.name as language,\r\n"
					+ "GROUP_CONCAT(concat(a.first_name,\" \",a.last_name)) as actors\r\n"
					+ "from film f\r\n"
					+ "join film_actor fa\r\n"
					+ "on f.film_id=fa.film_id\r\n"
					+ "join actor a\r\n"
					+ "on a.actor_id=fa.actor_id\r\n"
					+ "join language l\r\n"
					+ "on l.language_id=f.language_id\r\n"
					+ "join ( select fc.film_id,c.name from  film_category fc\r\n"
					+ "  join category c\r\n"
					+ "  on fc.category_id = c.category_id) fcc\r\n"
					+ "  on fcc.film_id=f.film_id\r\n"
					+" group by f.film_id;";
			//Execute the query.
			System.out.print(sql);
	        ResultSet result = stmt.executeQuery(sql);
	        while(result.next())
	        {
	        // create a new object and fill the field with the values from the result set.
	        movie  = new Movie(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7));
	        //movie.setMovieName(result.getString(1));
	        //add movie to movielist
	        movieList.add(movie);
	        
	        }
	        stmt.close();
	        DBUtil.closeDBConnection(conn);
	
			} 
		catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}
			return movieList;
	}
	
	public ArrayList<Movie> getMovieDetailsByName(String movieName)
	{
		ArrayList<Movie> movieList=new ArrayList<Movie>();
		Movie movie = null;
		try {

			Connection conn = DBUtil.getDBConnection();
			Statement stmt = conn.createStatement(
	        		ResultSet.TYPE_SCROLL_INSENSITIVE,
	        		ResultSet.CONCUR_READ_ONLY);
			
			String sql = "select f.film_id,f.title as movie_name,f.description, f.release_year,fcc.name as genre, l.name as language,\r\n"
					+ "GROUP_CONCAT(concat(a.first_name,\" \",a.last_name)) as actors\r\n"
					+ "from film f\r\n"
					+ "join film_actor fa\r\n"
					+ "on f.film_id=fa.film_id\r\n"
					+ "join actor a\r\n"
					+ "on a.actor_id=fa.actor_id\r\n"
					+ "join language l\r\n"
					+ "on l.language_id=f.language_id\r\n"
					+ "join ( select fc.film_id,c.name from  film_category fc\r\n"
					+ "  join category c\r\n"
					+ "  on fc.category_id = c.category_id) fcc\r\n"
					+ "  on fcc.film_id=f.film_id\r\n"
					+" WHERE f.title  like '%"+ movieName +"%' group by f.film_id;";
			
			//Execute the query.
			System.out.print(sql);
	        ResultSet result = stmt.executeQuery(sql);
	        while (result.next())
	        {
	        // create a new object and fill the field with the values from the result set.
	        movie  = new Movie(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7));
	        movieList.add(movie);
	        System.out.println(movie.getMovieName());
        	//result.next();
	        }
	        stmt.close();
	        DBUtil.closeDBConnection(conn);
	        
			} 
		catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}
		return movieList;
	}

	public ArrayList<Movie> getMovieDetailsByIDs(ArrayList<Integer> movieIds) {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		Movie movie = null;
		try {

			Connection conn = DBUtil.getDBConnection();
			Statement stmt = conn.createStatement(
	        		ResultSet.TYPE_SCROLL_INSENSITIVE,
	        		ResultSet.CONCUR_READ_ONLY);
			String ids = "";
			for(int i = 0;i<movieIds.size();i++) 
			{
				ids = ids+movieIds.get(i)+",";
			}
			ids=ids.substring(0, ids.length() - 1);
			//System.out.println("ids:"+ids);
			String sql = "select f.film_id,f.title as movie_name,f.description, f.release_year,fcc.name as genre, l.name as language,\r\n"
					+ "GROUP_CONCAT(concat(a.first_name,\" \",a.last_name)) as actors\r\n"
					+ "from film f\r\n"
					+ "join film_actor fa\r\n"
					+ "on f.film_id=fa.film_id\r\n"
					+ "join actor a\r\n"
					+ "on a.actor_id=fa.actor_id\r\n"
					+ "join language l\r\n"
					+ "on l.language_id=f.language_id\r\n"
					+ "join ( select fc.film_id,c.name from  film_category fc\r\n"
					+ "  join category c\r\n"
					+ "  on fc.category_id = c.category_id) fcc\r\n"
					+ "  on fcc.film_id=f.film_id\r\n"
					+" WHERE f.film_id in ("+ids +") group by f.film_id;";
			
			//Execute the query.
			System.out.print(sql);
	        ResultSet result = stmt.executeQuery(sql);
	        while(result.next())
	        {
	        // create a new object and fill the field with the values from the result set.
	        	movie  = new Movie(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7));
	        //movie.setMovieName(result.getString(1));
	        //add movie to movielist
	        movieList.add(movie);
	        
	        }
	        stmt.close();
	        DBUtil.closeDBConnection(conn);
	
			} 
		catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}
		return movieList;
	}

	
	
}
