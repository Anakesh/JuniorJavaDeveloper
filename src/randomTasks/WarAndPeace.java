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

        Map<Character,Integer> eachCharNum = new HashMap<Character,Integer>();
        Map<String,int[]> words = new HashMap<>();
        Map<Integer, ArrayList<String>> wordsByCharNum = new HashMap<>();
        Map<Integer,List<String>> wordsByCharNumNoStopWords = new HashMap<>();
        Map<String,Integer> numOfEachWord = new HashMap<>();
        StringBuilder currentWord = new StringBuilder();
        while((i = newFileReader.read())!=-1) {
            character = (char) i;
            if (Character.isAlphabetic(character)) {
                currentWord.append(character);
                Integer charNum = eachCharNum.get(character);
                eachCharNum.put(character,(charNum==null)?1:charNum+1);
            }
            else if(currentWord.length()!=0){
                Integer numOfWord = numOfEachWord.get(currentWord.toString());
                numOfEachWord.put(currentWord.toString(),(numOfWord==null)?1:numOfWord+1);
                ArrayList<String> listOfWordsByCharNum = wordsByCharNum.get(currentWord.length());
                if(listOfWordsByCharNum==null){
                    ArrayList<String> newList = new ArrayList<>();
                    newList.add(currentWord.toString());
                    wordsByCharNum.put(currentWord.length(),newList);
                }
                else{
                    if(!listOfWordsByCharNum.contains(currentWord.toString()))
                    {
                        listOfWordsByCharNum.add(currentWord.toString());
                        wordsByCharNum.put(currentWord.length(),listOfWordsByCharNum);
                    }
                }
            }
        }
        //Collection<Integer> sortedList = Collections.sort(eachCharNum.values());
        //Collections.max(eachCharNum.values());

    }
}
class word{

}
