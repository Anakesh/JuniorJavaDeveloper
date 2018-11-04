package randomTasks;

import randomTasks.TxtFileParser.TxtFileParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println(System.getProperty("user.dir")+"\\wp.txt");
        TxtFileParser txtFileParser = new TxtFileParser();
        txtFileParser.ReadFile(System.getProperty("user.dir")+"\\wp.txt",3);
        System.out.println(txtFileParser.getTopTenWordsInString());
        System.out.println(txtFileParser.getTopTenPhrasesInString());
        System.out.println(txtFileParser.getPhraseQuantity("war and peace"));
        System.out.println(txtFileParser.getPhraseQuantity("he did not "));

    }
}
