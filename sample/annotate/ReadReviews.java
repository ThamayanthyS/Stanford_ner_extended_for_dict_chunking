package sample.annotate;

import java.sql.*;
import java.util.ArrayList;


public class ReadReviews {

    static final double CHUNK_SCORE = 1.0;
    String db = "synergy_training_sample";
    public static ArrayList<String> reviewList = new ArrayList<String>();


    public static void main(String[] a) {
        new ReadReviews().readReviewFromDB();
    }

    public void readReviewFromDB() {
        try {
            Connection con = null;
            con = DBConnection.getConnection();
            try {
                Statement st = con.createStatement();
                ResultSet res = st.executeQuery
                        ("SELECT review FROM " + "reviews" + " NATURAL JOIN " + "restaurants" + " WHERE rest_name =" + "\"" + Const.resturant[3]  + "\""+ "OR "+ "rest_name ="+ "\"" + Const.resturant[0]  + "\""+ "OR " + "rest_name ="+ "\"" + Const.resturant[2]  + "\""+ "OR " + "rest_name ="+ "\"" + Const.resturant[1]  + "\"");

//                ResultSet res = st.executeQuery("SELECT review FROM " + "reviews " +"WHERE "+ "rest_id = "+ "\""+);

                while (res.next()) {
                    String review = res.getString("review");
                    Const.reviewList.add(review);

                }
            } catch (SQLException s) {
                System.out.println("SQL statement is not executed!" + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readReviewFromDB(String restaurant) {
        try {
            Connection con = null;
            con = DBConnection.getConnection();
            try {
                Statement st = con.createStatement();
//                ResultSet res = st.executeQuery
//                        ("SELECT review FROM " + "reviews" + " NATURAL JOIN " + "restaurants" + " WHERE rest_name =" + "\"" + Const.resturant[3]  + "\""+ "OR "+ "rest_name ="+ "\"" + Const.resturant[0]  + "\""+ "OR " + "rest_name ="+ "\"" + Const.resturant[2]  + "\""+ "OR " + "rest_name ="+ "\"" + Const.resturant[1]  + "\"");

                ResultSet res = st.executeQuery("SELECT review FROM " + "reviews " +"WHERE "+ "rest_id = "+ "\""+restaurant+"\"");

                while (res.next()) {
                    String review = res.getString("review");
                    reviewList.add(review);

                }
            } catch (SQLException s) {
                System.out.println("SQL statement is not executed!" + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
