package sample.clean;

import sample.annotate.DBConnection;
import sample.annotate.WriteFile;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thamayanthy on 11/17/2014.
 */
public class cleanDb {
    public static File file;
    public static FileWriter fw;
    public static BufferedWriter bw;
    public static PrintWriter printWriter;
    public static BufferedReader br;
    public static List<String> list = new ArrayList<String>();

    public static void findConflicts() {

        try {
            String file_name = "sample/text/output.txt";
            String file_name1 = "sample/text/differ.txt";
            file = WriteFile.fileCreate(file_name1);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            br = new BufferedReader(new FileReader(file_name));
            String line1 = br.readLine();
            int x = 0;

            while (line1 != null && line1.length() != 0) {
                if (line1.contains("\tFOOD\tFOOD")){//("\tO\tFOOD") ){//|| line1.contains("\tFOOD\tO")) {
                    list.add(line1.split("\t")[0] + "\n");
                }
                line1 = br.readLine();
            }
            for (String list_temp : list) {
                bw.write(list_temp);
            }
            bw.flush();
//            clearDb();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearDb() {
        try {
            Connection con = null;
            con = DBConnection.getConnection();
            String file_name1 = "sample/text/differ.txt";
            // file = WriteFile.fileCreate(file_name1);
            br = new BufferedReader(new FileReader(file_name1));
            String line1 = br.readLine();
            int x = 0;
            Statement st = con.createStatement();
            PreparedStatement ps;//=con.prepareStatement("DELETE FROM food_name_list_all WHERE food_name = ?");


            while(line1!=null || line1.length()>0){
                try {
                    con.setAutoCommit(false);
                    System.out.println(line1);
                    ps = con.prepareStatement("DELETE FROM food_name_list_all WHERE food_name = ?");
                    ps.setString(1, line1);
                    ps.executeUpdate();
                    con.commit();
                    line1 = br.readLine();
//                        ResultSet res = st.executeQuery
//                                ("DELETE FROM food_name_list_all WHERE food_name = " + "\"" + food + "\"");
                } catch (SQLException s) {
                    System.out.println("SQL statement is not executed!" + s);
                }
            }


            System.out.println(line1);


        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }
}
