package sample.annotate;

import java.util.ArrayList;


public class Const {
public static ArrayList<String> reviewList=new ArrayList<String>();
public static String[] resturant={"Pine Cone Restaurant","Deforest Family Restaurant","Chang Jiang Chinese Kitchen","Green Lantern Restaurant"};

    public static ArrayList<String> getReviewList() {
        return reviewList;
    }

    public static void setReviewList(ArrayList<String> reviewList) {
        Const.reviewList = reviewList;
    }
}
