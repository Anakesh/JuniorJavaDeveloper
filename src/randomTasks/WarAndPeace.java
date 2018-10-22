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
        List<String> topTenWords = new ArrayList<>();
        long totalCharacterCount=0;

        while((i = newFileReader.read())!=-1) {
            character =Character.toLowerCase((char) i);
            if (Character.isAlphabetic(character)) {
                currentWord.append(character);
                Integer charNum = eachCharNum.get(character);
                eachCharNum.put(character,(charNum==null)?1:charNum+1);
                totalCharacterCount++;
            }
            else if(currentWord.length()!=0){
                currentStrWorld = currentWord.toString();
                Integer numOfWord = numOfEachWord.get(currentStrWorld);
                numOfEachWord.put(currentStrWorld,(numOfWord==null)?1:numOfWord+1);
                if(topTenWords.contains(currentStrWorld)){
                    int currentWordIndex = topTenWords.indexOf(currentStrWorld);
                    if(currentWordIndex>0 && numOfEachWord.get(topTenWords.get(currentWordIndex-1))<numOfEachWord.get(topTenWords.get(currentWordIndex))){
                        String buff = topTenWords.get(currentWordIndex-1);
                        topTenWords.set(currentWordIndex-1,topTenWords.get(currentWordIndex));
                        topTenWords.set(currentWordIndex,buff);
                    }
                }
                else {
                    /*
                    for (int j = 0; j < topTenWords.size(); j++) {
                        if (topTenWords[j] == null || numOfEachWord.get(currentStrWorld) > numOfEachWord.get(topTenWords[j])) {
                            String buff1 = topTenWords[j];
                            String buff2;
                            topTenWords[j] = currentStrWorld;
                            j++;
                            while (j < topTenWords.length - 2) {
                                buff2 = topTenWords[j];
                                topTenWords[j] = buff1;
                                j++;
                                buff1 = topTenWords[j];
                                topTenWords[j] = buff2;
                                j++;
                            }
                        }
                    }*/
                }

                wordsByCharNum = addWord(currentStrWorld,wordsByCharNum);
                if(!currentWord.toString().matches(articles))
                    wordsByCharNumNoStopWords = addWord(currentStrWorld,wordsByCharNumNoStopWords);
                currentWord = new StringBuilder();
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
