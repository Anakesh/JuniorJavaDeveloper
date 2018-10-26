package randomTasks;

import org.omg.CORBA.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WarAndPeace
{
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir")+"\\wp.txt");
        ReadFile(System.getProperty("user.dir")+"\\wp.txt");

    }

    public void PrintToConsole(String text){
        System.out.println(text);

    }
    public static void ReadFile(String path) throws IOException {
        FileReader newFileReader = new FileReader(path);
        int i;
        char character;
        String articles = "a|on|the|an|to|in|up|of";
        String currentStrWorld;

        Map<Character,Integer> eachCharNum = new HashMap<>();
        Map<Integer, ArrayList<String>> wordsByCharNum = new HashMap<>();
        Map<Integer,ArrayList<String>> wordsByCharNumNoStopWords = new HashMap<>();
        Map<String,Integer> numOfEachWord = new HashMap<>();
        StringBuilder currentWord = new StringBuilder();
        String[] topTenWords = new String[10];
        long totalCharacterCount=0;
        int counter = 0;
        while((i = newFileReader.read())!=-1) {
            character = Character.toLowerCase((char) i);
            if (character == '\'')
                continue;
            if (Character.isAlphabetic(character)) {
                currentWord.append(character);
                Integer charNum = eachCharNum.get(character);
                eachCharNum.put(character, (charNum == null) ? 1 : charNum + 1);
                totalCharacterCount++;
            } else if (currentWord.length() != 0) {
                currentStrWorld = currentWord.toString();
                Integer numOfWord = numOfEachWord.get(currentStrWorld);
                numOfEachWord.put(currentStrWorld, (numOfWord == null) ? 1 : numOfWord + 1);
                if (counter < topTenWords.length) {
                    topTenWords[counter] = currentStrWorld;
                } else {
                    int currentWordIndex = Arrays.binarySearch(topTenWords, currentStrWorld);
                    if (currentWordIndex > 0
                            && numOfEachWord.get(topTenWords[currentWordIndex - 1]) < numOfEachWord.get(topTenWords[currentWordIndex])) {
                        String buff = topTenWords[currentWordIndex - 1];
                        topTenWords[currentWordIndex - 1] = topTenWords[currentWordIndex];
                        topTenWords[currentWordIndex] = buff;
                    } else {
                        for(int j=0;j<topTenWords.length;j++){
                            if (numOfEachWord.get(currentStrWorld) > numOfEachWord.get(topTenWords[j])) {
                                String[] buffStr = new String[j];
                                System.arraycopy(topTenWords, j, buffStr, j, topTenWords.length - j - 1);
                                topTenWords[j] = currentStrWorld;
                                System.arraycopy(buffStr, j, topTenWords, j + 1, topTenWords.length - j - 1);
                            }
                        }
                    }
                }
                wordsByCharNum = addWord(currentStrWorld, wordsByCharNum);
                if (!currentWord.toString().matches(articles))
                    wordsByCharNumNoStopWords = addWord(currentStrWorld, wordsByCharNumNoStopWords);
                currentWord = new StringBuilder();
                if (counter < topTenWords.length)
                    counter++;
            }
        }
        System.out.println("");
    }

    private static Map<Integer,ArrayList<String>> addWord(String word,Map<Integer,ArrayList<String>> hashMap){
        ArrayList<String> listOfWordsByCharNum = hashMap.get(word.length());
        if(listOfWordsByCharNum==null){
            ArrayList<String> newList = new ArrayList<>();
            newList.add(word.toString());
            hashMap.put(word.length(),newList);
        }
        else{
            if(!listOfWordsByCharNum.contains(word.toString()))
            {
                listOfWordsByCharNum.add(word.toString());
                hashMap.put(word.length(),listOfWordsByCharNum);
            }
        }
        return hashMap;
    }
}
class word{

}
