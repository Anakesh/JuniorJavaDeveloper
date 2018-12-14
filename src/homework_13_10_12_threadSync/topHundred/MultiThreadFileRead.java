package homework_13_10_12_threadSync.topHundred;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiThreadFileRead {
    private Map<String,Integer> allWordMap;

    private LinkedHashMap<String,Integer> topHundred;

    public void readFile(File file) throws FileNotFoundException {
        if(file.exists()&&file.isFile()){
            int numOfThreads = Runtime.getRuntime().availableProcessors();
            long fileLength = file.length();
            long partLength =  (fileLength%numOfThreads==0)? fileLength/((long)numOfThreads):(fileLength/((long)numOfThreads))+1;
            FileReadThread[] threads = new FileReadThread[numOfThreads];
            for(int i =0;i<numOfThreads;i++){
                threads[i] = new FileReadThread(file,i*partLength,partLength);
            }
            for (FileReadThread thread:threads){
                thread.run();
            }
            for (FileReadThread thread:threads){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (FileReadThread thread:threads){
                addWordsFromMap(thread.getWordMap());
            }
            topHundred = new LinkedHashMap<>();
            List<Integer> list = new ArrayList<>();
            Stream<Map.Entry<String,Integer>> stream = allWordMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(100);
            stream.forEach(e->topHundred.put(e.getKey(),e.getValue()));
//            stream.forEach(e->list.add(e.getValue()));
//            System.out.println();
        } else
            throw new FileNotFoundException();
    }

    private void addWordsFromMap(Map<String,Integer> map){
        if(allWordMap==null)
            allWordMap = map;
        else{
            for(Map.Entry<String,Integer> entry:map.entrySet()){
                String key = entry.getKey();
                if(allWordMap.containsKey(key)){
                    allWordMap.put(key,allWordMap.get(key)+entry.getValue());
                } else {
                    allWordMap.put(key,entry.getValue());
                }
            }
        }
    }

    public LinkedHashMap<String, Integer> getTopHundred() {
        return topHundred;
    }
}
