/**
 * @author:Nirupama Mohan Latha
 * Purpose: Class for getting recommended list for the user
 */

package RecommenderSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;

public class Recommender {

	public ArrayList<Integer> getRecommededMovies(int userID) {
		ArrayList<Integer> recommendedList = new ArrayList<Integer>();
	try {
		DataModel model = new FileDataModel(new File("user_ratings.csv"));
		CityBlockSimilarity similarity = new CityBlockSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1,similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		  
     		// The First argument is the userID and the Second parameter is 'HOW MANY'
      		List<RecommendedItem> recommendations = recommender.recommend(userID, 5);      
     		for (RecommendedItem recommendation : recommendations) {
     			recommendedList.add((int) recommendation.getItemID());
			System.out.println(recommendation.getItemID());
		}
		} catch (Exception e) {
			System.out.println("Exception occured !");
		}
	return recommendedList;

	}

}

