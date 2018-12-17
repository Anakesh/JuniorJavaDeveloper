package homework_14_14_12_safeColl.blockingQueueTop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        MultiThreadFileRead multiThreadFileRead = new MultiThreadFileRead();
        try {
            multiThreadFileRead.readFile(new File("wp.txt"));
            TreeSet<Word> topHundred = multiThreadFileRead.getTopHundredWordSet();
            for(Word word:topHundred){
                System.out.println(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

