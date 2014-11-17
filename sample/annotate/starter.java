package sample.annotate;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunking;
import com.aliasi.dict.ExactDictionaryChunker;
import com.aliasi.dict.MapDictionary;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;

import java.io.*;

/**
 * Created by Thamayanthy on 11/15/2014.
 */
public class starter {
    public static File file;
    public static FileWriter fw;
    public static BufferedWriter bw;
    public static PrintWriter printWriter;

    public static void main(String... args) {
        MapDictionary<String> dictionary = new MapDictionary<String>();
        new ReadFoodItem().readReviewFromDB(dictionary);
        new ReadReviews().readReviewFromDB();
        WriteFile.writeTxt();
        BufferedReader br;
        Restaurants.getAllRestaurantNames();


        //to run the command line process
//        List<String> commands = new ArrayList<String>();
//        commands.add("cd \\.");
//        commands.add("cd Users\\Thamayanthy\\Desktop\\FYP\\project folders\\stanford-ner-2014-10-26");
//        commands.add("java -cp stanford-ner.jar edu.stanford.nlp.process.PTBTokenizer sample/text/reviewlist.txt > sample/tok/reviewlist.tok");
//
//        SystemCommandExecutor commandExecutor = new SystemCommandExecutor(commands);
//        int result = commandExecutor.executeCommand();
//
//        StringBuilder stdout = commandExecutor.getStandardOutputFromCommand();
//        StringBuilder stderr = commandExecutor.getStandardErrorFromCommand();

        try {
//            br = new BufferedReader(new FileReader("sample/tok/reviewlist_test.tok"));
            br = new BufferedReader(new FileReader("sample/tok/reviewlist_test.tok"));
            String line1 = br.readLine();
//            String file_name = "sample/tsv/reviewlist_test.tsv";
            String file_name = "sample/tsv/reviewlist_test.tsv";
            WriteFile writeFile = new WriteFile();
            writeFile.fileCreate(file_name);

            while (line1 != null) {

                ExactDictionaryChunker dictionaryChunkerFF
                        = new ExactDictionaryChunker(dictionary,
                        IndoEuropeanTokenizerFactory.INSTANCE,
                        false, false);
                Chunking chunking = dictionaryChunkerFF.chunk(line1);
                String temp = line1;

//                System.out.println("size" + chunking.chunkSet().size());
                if (chunking.chunkSet().size() > 0) {
                    for (Chunk chunk : chunking.chunkSet()) {

                        if ("FOOD".equals(chunk.type())) {
                            temp = temp + "\t" + "FOOD";
                            System.out.println(temp);
                        } else {
                            temp = temp + "\t" + "O";
                        }
                    }
                }else{
                    temp = temp + "\t" + "O";
                }
                writeFile.writeTsv(temp + "\n");


                line1 = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void test(){
        new ReadReviews().readReviewFromDB();
        WriteFile.writeTxt();
        BufferedReader br;
    }

}