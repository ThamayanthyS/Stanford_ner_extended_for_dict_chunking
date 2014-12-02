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
//        tagReviews();
//        findConflicts();
//        clearDb();
        makeSentence();
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

//        String training_tok_file_name = "sample/tok/reviewlist.tok";
//        String test_tok_file_name = "sample/tok/reviewlist_test.tok";

        String training_tok_file_name = "sample/text/reviewlist.txt";
        String test_tok_file_name = "sample/text/reviewlist_test.txt";

        File training_tok_file = WriteFile.fileCreate(training_tok_file_name);
        File test_tok_file = WriteFile.fileCreate(test_tok_file_name);

        String training_tsv_file_name = "sample/tsv/reviewlist.tsv";
        String test_tsv_file_name = "sample/tsv/reviewlist_test.tsv";

        File training_tsv_file = WriteFile.fileCreate(training_tsv_file_name);
        File test_tsv_file = WriteFile.fileCreate(test_tsv_file_name);

        tag(training_tok_file, training_tsv_file);
        tag(test_tok_file, test_tsv_file);

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
                String temp = "";// = line1;
//                System.out.println(line1);
                int pre = 0;
                int last = 0;
                String non_food[];

                if (chunking.chunkSet().size() > 0) {
                    for (Chunk chunk : chunking.chunkSet()) {
                        if (chunk.start() > pre) {
                            non_food = line1.substring(pre, chunk.start() - 1).split(" ");
                            for (String o : non_food) {
                                if (o.length() > 0 || !o.equals(null))
                                    temp = temp + o + "\t" + "O" + "\n";
                            }
                        }
                        //last=chunk.end();

                        if ("FOOD".equals(chunk.type())) {
                            if (line1.substring(chunk.start(), chunk.end()).length() > 0 || !line1.substring(chunk.start(), chunk.end()).equals(null))
                                temp = temp + line1.substring(chunk.start(), chunk.end()) + "\t" + "FOOD" + "\n";
                            System.out.println(temp);
                        }
//                        else {
//                            if (o.length() > 0 || !o.equals(null))
//                            temp = temp + "\t" + "O" + "\n";
//                        }
                        pre = chunk.end();
                    }
                } else {
                    non_food = line1.split(" ");
                    for (String o : non_food) {
                        if (o.length() > 0 || !o.equals(null))
                            temp = temp + o + "\t" + "O" + "\n";
                    }
                    pre = pre + line1.length();
                }
                list.add(temp);
//                line1 = br.readLine();

                temp = "";
            }
            WriteFile.writeTsv(destFile, list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void makeSentence() {
//        String dest_sentence = "sample/text/sentence.txt";
//        String source_sentence = "sample/text/reviewlist.txt";
//
//        File dest_file = WriteFile.fileCreate(dest_sentence);
//        File source_file = WriteFile.fileCreate(source_sentence);
//
//        new ReadFoodItem().readReviewFromDB(dictionary);
//
//        try {
//            br = new BufferedReader(new FileReader(source_file));
//            String line1 = br.readLine();
//            System.out.println("line" + line1);
//            int a = 0;
//            List<String> list = new ArrayList<String>();
//            ExactDictionaryChunker dictionaryChunkerFF
//                    = new ExactDictionaryChunker(dictionary,
//                    IndoEuropeanTokenizerFactory.INSTANCE,
//                    false, false);
//
//            while (line1 != null) {
//                if (line1 != "\n") {
//                    String[] sentences = line1.split("\\.");
//                System.out.println("line"+line1);
//                    for (String s : sentences) {
//                        s.trim();
//                        if(s.startsWith(" ")){
//                            s=s.substring(1,s.length());
//                        }
//                        String temp = "FOOD\t[ ";
//                        if (s != null && s.length() > 1 ) {
//                            System.out.println("sentence" + s);
//
//
//                            Chunking chunking = dictionaryChunkerFF.chunk(s);
//
//
//                            int chunk_size = chunking.chunkSet().size();
//
//                            if (chunk_size > 0) {
//                                int count = 0;
//                                for (Chunk chunk : chunking.chunkSet()) {
//                                    count++;
//                                    if ("FOOD".equals(chunk.type())) {
//                                        if (count > 1)
//                                            temp = temp + ", ";
//                                        temp = temp + s.substring(chunk.start(), chunk.end());
//                                    }
//                                }
//
//                            } else {
//                                temp = temp + "O ";
//                            }
//                            list.add(temp + " ]\t" + s + "\n");
//                            temp = "";
//                        }
//                    }
//
//                    line1 = br.readLine();
////                line1 = "pizza pizza";
////
////                a++;
////                if(a==10)
////                    line1=null;
//                }
//            }
//            WriteFile.writeTsv(dest_file, list);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
