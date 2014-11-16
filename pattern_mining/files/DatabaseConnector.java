package pattern_mining.files;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by brusoth on 11/7/2014.
 */
public class DatabaseConnector {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/synergy_training_sample";

    static final String USER = "root";
    static final String PASS = "";

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;

    public void connect()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void disconect()
    {
        try {
            conn.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getRestaurantReviews(String res_name)
    {
        String query="SELECT *FROM "+"reviews"+" NATURAL JOIN "+"restaurants"+" WHERE rest_name =?";

        ArrayList<String> list=new ArrayList<String>();

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,res_name);
            ResultSet res = pstmt.executeQuery();
            while(res.next()){
                String review = res.getString("review");
                System.out.println(review);
                list.add(review);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<String> getRestaurants()
    {
        ArrayList<String> list=new ArrayList<String>();
        String query="SELECT rest_name FROM restaurants";

        try {
            pstmt=conn.prepareStatement(query);
            ResultSet res = pstmt.executeQuery();
            while(res.next()){
                String rest = res.getString("rest_name");
                System.out.println(rest);
                list.add(rest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
