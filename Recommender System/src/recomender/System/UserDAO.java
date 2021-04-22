package RecommenderSystem;

public interface UserDAO {
	
	public boolean findUserByUsername(String username);
	
	public User getUserDetailsByUsername(String username);
	
	public boolean insertUserDetails(User user);
	
	public boolean updateUserDetails(User user);
	
	public boolean deleteUserDetails(User user);

}
