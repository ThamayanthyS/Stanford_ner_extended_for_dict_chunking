package sample.clean;

import sample.annotate.WriteFile;

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
    public static List<String> list = new ArrayList<String>();

    public static void main(String args[]) {

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

//                System.out.println(line1.split("\t")[0]);
                if (line1.contains("\tO\tFOOD")) {
                    list.add(line1.split("\t")[0] + "\n");
                    System.out.println(line1.split("\t")[0]);
                }
                line1 = br.readLine();
            }
            for (String list_temp : list) {
                bw.write(list_temp);
            }
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void clearDb(){

    }
}
