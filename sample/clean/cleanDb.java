package sample.clean;

import java.io.*;
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
    public static List<String> list =new ArrayList<String>();

    public static void main(String args[]){

        try {
            String file_name = "sample/text/output.txt";
            br = new BufferedReader(new FileReader(file_name));
            String line1 = br.readLine();
            int x=0;

            while(line1!=null && line1.length()!=0){

//                System.out.println(line1.split("\t")[0]);
                if(line1.contains("\tO\tFOOD")){
                    list.add(line1.split("\t")[0]);
                    System.out.println(line1.split("\t")[0]);
                }
                line1 = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
