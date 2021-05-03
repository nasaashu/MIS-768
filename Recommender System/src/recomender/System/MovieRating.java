package RecommenderSystem;

public class MovieRating extends Movie{
	private int rating;
	private String comment;
	private User user;
	
	public MovieRating(int mRating)
	{
		this.rating=mRating;
	}
	
	public MovieRating() {}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
