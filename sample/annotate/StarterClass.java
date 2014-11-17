package sample.annotate;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunking;
import com.aliasi.dict.ExactDictionaryChunker;
import com.aliasi.dict.MapDictionary;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thamayanthy on 11/17/2014.
 */
public class StarterClass {
    public static int rest_size;
    public static File trainingFile;
    public static File testFile;
    public static BufferedReader br;
    public static MapDictionary<String> dictionary = new MapDictionary<String>();

    public static void main(String[] args) {
//        collectReviews();
        tagReviews();
    }

    public static void collectReviews() {
        String training_file_name = "sample/text/reviewlist.txt";
        String test_file_name = "sample/text/reviewlist_test.txt";

        trainingFile = WriteFile.fileCreate(training_file_name);
        testFile = WriteFile.fileCreate(test_file_name);

        Restaurants.getAllRestaurantNames();
        rest_size = Restaurants.getRestaurants().size();

        System.out.println(rest_size);

        for (int i = 0; i < Math.min(rest_size, 100); i++) {
            if (i % 2 == 0) {
                ReadReviews.readReviewFromDB(Restaurants.getRestaurants().get(i));
                WriteFile.writeToFile(trainingFile, ReadReviews.reviewList);
            } else {
                ReadReviews.readReviewFromDB(Restaurants.getRestaurants().get(i));
                WriteFile.writeToFile(testFile, ReadReviews.reviewList);
            }
        }
    }

    public static void tagReviews() {

        new ReadFoodItem().readReviewFromDB(dictionary);

        String training_tok_file_name = "sample/tok/reviewlist.tok";
        String test_tok_file_name = "sample/tok/reviewlist_test.tok";

        File training_tok_file = WriteFile.fileCreate(training_tok_file_name);
        File test_tok_file = WriteFile.fileCreate(test_tok_file_name);

        String training_tsv_file_name = "sample/tsv/reviewlist.tsv";
        String test_tsv_file_name = "sample/tsv/reviewlist_test.tsv";

        File training_tsv_file = WriteFile.fileCreate(training_tsv_file_name);
        File test_tsv_file = WriteFile.fileCreate(test_tsv_file_name);

        tag(training_tok_file,training_tsv_file);
        tag(test_tok_file,test_tsv_file);

    }

    public static void tag(File sourceFile, File destFile) {

        try {
            br = new BufferedReader(new FileReader(sourceFile));
            String line1 = br.readLine();
            List<String> list = new ArrayList<String>();

            while (line1 != null) {

                ExactDictionaryChunker dictionaryChunkerFF
                        = new ExactDictionaryChunker(dictionary,
                        IndoEuropeanTokenizerFactory.INSTANCE,
                        false, false);
                Chunking chunking = dictionaryChunkerFF.chunk(line1);
                String temp = line1;
                System.out.println(line1);
                if (chunking.chunkSet().size() > 0) {
                    for (Chunk chunk : chunking.chunkSet()) {

                        if ("FOOD".equals(chunk.type())) {
                            temp = temp + "\t" + "FOOD";
                            System.out.println(temp);
                        } else {
                            temp = temp + "\t" + "O";
                        }
                    }
                } else {
                    temp = temp + "\t" + "O";
                }
                list.add(temp + "\n");
                line1 = br.readLine();
            }
            WriteFile.writeTsv(destFile, list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
