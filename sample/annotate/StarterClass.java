package sample.annotate;

import java.io.File;

/**
 * Created by Thamayanthy on 11/17/2014.
 */
public class StarterClass {
    public static int rest_size;
    public static File trainingFile;
    public static File testFile;

    public static void main(String[] args){
//        collectReviews();
        tagReviews();
    }

    public static void collectReviews(){
        String training_file_name="sample/text/reviewlist.txt";
        String test_file_name="sample/text/reviewlist_test.txt";

        trainingFile=WriteFile.fileCreate(training_file_name);
        testFile=WriteFile.fileCreate(test_file_name);

        Restaurants.getAllRestaurantNames();
        rest_size=Restaurants.getRestaurants().size();

        System.out.println(rest_size);

        for(int i=0;i<Math.min(rest_size, 100);i++){
            if(i%2==0){
                ReadReviews.readReviewFromDB(Restaurants.getRestaurants().get(i));
                WriteFile.writeToFile(trainingFile,ReadReviews.reviewList);
            }else {
                ReadReviews.readReviewFromDB(Restaurants.getRestaurants().get(i));
                WriteFile.writeToFile(testFile,ReadReviews.reviewList);
            }
        }
    }

    public static void tagReviews(){
        
    }
}
