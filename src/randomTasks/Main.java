package randomTasks;

import randomTasks.TxtFileParser.TxtFileParser;
import randomTasks.TxtFileParser.Word;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Word firstWord = new Word("First");
        firstWord.incrementQuantity();
        Word secondWord = new Word("First");
        System.out.println(firstWord.equals(secondWord));
        secondWord.incrementQuantity();
        System.out.println(firstWord.equals(secondWord));

        System.out.println(System.getProperty("user.dir")+"\\wp.txt");
        TxtFileParser txtFileParser = new TxtFileParser();
        txtFileParser.ReadFile(System.getProperty("user.dir")+"\\wp.txt",3);
//        System.out.println(txtFileParser.getTopTenWords());
//        System.out.println(txtFileParser.getTopTenPhrasesInString());
        System.out.println(txtFileParser.getPhraseQuantity("war and peace"));
        System.out.println(txtFileParser.getPhraseQuantity("he did not "));


    }
}
