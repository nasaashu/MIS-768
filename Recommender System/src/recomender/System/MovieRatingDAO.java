package RecommenderSystem;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface MovieRatingDAO {
	public MovieRating getMovieRatingByUserID(String userID,int i);
	public boolean saveMovieRating(int oldRating,MovieRating mRating);
	public ArrayList<MovieRating> getMovieRatingsAll(); 
}
