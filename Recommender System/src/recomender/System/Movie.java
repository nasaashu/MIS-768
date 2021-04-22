package RecommenderSystem;

public class Movie {
	private String movieName;
	private String description;
	private String year;
	private String genre;
	private String language;
	private String actors;
	
	public Movie() {}

	
	public Movie(String movieName, String description, String year, String genre, String language, String actors) {
		super();
		this.movieName = movieName;
		this.description = description;
		this.year = year;
		this.genre = genre;
		this.language = language;
		this.actors = actors;
	}

	public String getMovieName() {
		return movieName;
	}


	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getActors() {
		return actors;
	}


	public void setActors(String actors) {
		this.actors = actors;
	}

	public String toString()
	{
		String str=null;
		
		str="Movie : "+movieName+"\tDescription : "+description+"\tYear : "+year+"\tGenre : "+genre+"\tLanguage : "+language+"\tActors : "+actors;
		return str;
		
	}
}
