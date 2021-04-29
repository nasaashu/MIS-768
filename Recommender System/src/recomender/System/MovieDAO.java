package RecommenderSystem;

import java.util.ArrayList;

public interface MovieDAO {

	public ArrayList<Movie> getMovieListAll();
	public Movie getMovieDetailsByName(String movieName);
	public ArrayList<Movie> getMovieDetailsByIDs(ArrayList<Integer> movieIds);
	
}
