package sample.annotate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thamayanthy on 11/17/2014.
 */
public class Restaurants {

    public static List<String> restaurants=new ArrayList<String>();

    public static List<String> getRestaurants() {
        return restaurants;
    }

    public static void getAllRestaurantNames(){
        try {
            Connection con = null;
            con = DBConnection.getConnection();
            try {
                Statement st = con.createStatement();
                ResultSet res = st.executeQuery
                        ("SELECT rest_id FROM restaurants");

                while (res.next()) {
                    String result = res.getString("rest_id");
//                    System.out.println(result);
                    restaurants.add(result);
                }
            } catch (SQLException s) {
                System.out.println("SQL statement is not executed!" + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
