package sample.annotate;

import java.io.*;
import java.util.List;

/**
 * Created by Thamayanthy on 11/15/2014.
 */
public class WriteFile {

    public static File file;
    public static FileWriter fw;
    public static BufferedWriter bw;
    public static PrintWriter printWriter;

    public static File fileCreate(String file_name) {
        file = new File(file_name);


        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    public static void createPrintWriter(File file) {
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void writeTxt() {
        ReadReviews readReviews = new ReadReviews();
        readReviews.readReviewFromDB();

        try {

            File file = new File("sample/text/reviewlist_test.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (String text : Const.reviewList) {
                bw.write(text);
                System.out.println(text);
            }
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTsv(File file, List<String> list) {
        createPrintWriter(file);
        for (String text : list) {
            printWriter.print(text);
            printWriter.flush();
        }

    }

    public static void writeToFile(File file1, List<String> list) {
        try {

            File file_local = file1;
            // if file doesnt exists, then create it
            if (!file_local.exists()) {
                file_local.createNewFile();
            }

            FileWriter fw = new FileWriter(file_local.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (String text : list) {
                bw.write(text);
                System.out.println(text);
            }
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

