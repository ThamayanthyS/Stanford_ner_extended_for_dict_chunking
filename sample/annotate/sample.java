package sample.annotate;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Thamayanthy on 11/16/2014.
 */
public class sample {
    public static void main(String[] args) {
//        try {
//            Process p = Runtime.getRuntime().exec("ls C:\\Users\\Thamayanthy\\Desktop\\FYP");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Sun's ProcessBuilder and Process example

        ProcessBuilder pb = new ProcessBuilder("java -cp stanford-ner.jar edu.stanford.nlp.process.PTBTokenizer sample/text/reviewlist.txt > sample/tok/reviewlist.tok");
        Map<String, String> env = pb.environment();
        File file=new File("Users\\Thamayanthy\\Desktop\\FYP\\project folders\\stanford-ner-2014-10-26");
//        env.put("VAR1", "myValue");
//        env.remove("OTHERVAR");
//        env.put("VAR2", env.get("VAR1") + "suffix");
        pb.directory(file);
        try {
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Runtime rt = Runtime.getRuntime();
//
//        try {
//            Process pr = rt.exec("c:/notepad.exe");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
