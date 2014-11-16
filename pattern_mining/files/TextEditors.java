package pattern_mining.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by brusoth on 11/11/2014.
 */
public class TextEditors {

    public void writeTextFile(ArrayList<String> list)
    {
        File file = new File("F:/filename.txt");
        // if file doesnt exists, then create it
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                for(String s:list) {
                    bw.write(s);
                }
                bw.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }


    }
}
