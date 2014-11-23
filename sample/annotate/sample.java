package sample.annotate;

import com.aliasi.dict.ExactDictionaryChunker;
import com.aliasi.dict.MapDictionary;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;

/**
 * Created by Thamayanthy on 11/16/2014.
 */
public class sample {
    public static void main(String[] args) {

        MapDictionary<String> dictionary = new MapDictionary<String>();
        new ReadFoodItem().readReviewFromDB(dictionary);
        new ReadReviews().readReviewFromDB();
        ExactDictionaryChunker dictionaryChunkerTT
                = new ExactDictionaryChunker(dictionary,
                IndoEuropeanTokenizerFactory.INSTANCE,
                true,true);
        DictionaryChunker.chunk(dictionaryChunkerTT,"food pizza cake sfsd f sdf sd f sdf sd f sd fsfsdfsd f sdfsdfsdf sdfsdfsdf vfbvdfvdfbdfb");
/*
        ProcessBuilder pb = new ProcessBuilder("java -cp stanford-ner.jar edu.stanford.nlp.process.PTBTokenizer sample/text/reviewlist.txt > sample/tok/reviewlist.tok");
        Map<String, String> env = pb.environment();
        File file=new File("Users\\Thamayanthy\\Desktop\\FYP\\project folders\\stanford-ner-2014-10-26");

        pb.directory(file);
        try {
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }
}
