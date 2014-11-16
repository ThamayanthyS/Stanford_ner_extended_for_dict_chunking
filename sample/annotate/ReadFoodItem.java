package sample.annotate;

import com.aliasi.dict.DictionaryEntry;
import com.aliasi.dict.MapDictionary;

import java.sql.*;


public class ReadFoodItem {
	static final double CHUNK_SCORE = 1.0;
	public static void main(String[] ar)
	{
		//new ReadReviews().readReviewFromDB();
	}
	public void readReviewFromDB(MapDictionary<String> dictionary)
	{
		
		//ArrayList<String> foodname=new ArrayList<String>();
		Connection con = null;

		try{
		 con=DBConnection.getConnection();
		  try{
		  Statement st = con.createStatement();
		  ResultSet res = st.executeQuery
		("SELECT food_name FROM "+"food_name_list_all" );  //Join two tables
		  
		  while(res.next()){
		  String name = res.getString("food_name");
		//  foodname.add(name);
		  dictionary.addEntry(new DictionaryEntry<String>(name,"FOOD",CHUNK_SCORE));
		  //System.out.println(name );
		  }
		  }
		  catch (SQLException s){
		  System.out.println("SQL statement is not executed!"+s);
		  }
		 }
	      catch (Exception e){
	      e.printStackTrace();
	     }
	}
}
