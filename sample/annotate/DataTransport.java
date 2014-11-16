package sample.annotate;

import java.sql.*;

/**
 * Created by Thamayanthy on 11/10/2014.
 */
public class DataTransport {
    static Connection con = null;

    public static Connection geConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/synergy_training_sample", "root", "root");

            Statement st = con.createStatement();
            ResultSet res = st.executeQuery
                    ("SELECT food_name FROM " + "food_items_oxford");  //Join two tables


            System.out.println("SQL statement is not executed!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }


}