package homework_14_14_12_safeColl.blockingQueueTop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiThreadFileRead {
    private Map<String,Word> allWordMap;
    private TreeSet<Word> topHundredWordSet;

    public void readFile(File file) throws FileNotFoundException {
        if(file.exists()&&file.isFile()){
            int numOfThreads = Runtime.getRuntime().availableProcessors();
            long fileLength = file.length();
            long partLength =  (fileLength%numOfThreads==0)? fileLength/((long)numOfThreads):(fileLength/((long)numOfThreads))+1;
            BlockingQueue<String> wordQueue = new LinkedBlockingQueue<>();
            FileReaderThread[] threads = new FileReaderThread[numOfThreads];
            for(int i =0;i<numOfThreads;i++){
                threads[i] = new FileReaderThread(file,i*partLength,partLength, wordQueue);
            }
            for (FileReaderThread thread:threads){
                thread.start();
            }
            int numofStopedThreads = 0;
            allWordMap = new HashMap<>();
            topHundredWordSet = new TreeSet<>();
            while(!wordQueue.isEmpty()||numofStopedThreads<numOfThreads){
                try {
                    String currentWord = wordQueue.take();
                    if(currentWord.equals(""))
                        numofStopedThreads++;
                    else{
//                        Word wordInMap = allWordMap.computeIfAbsent(currentWord, (k)->new Word(k));
                        Word wordInMap = allWordMap.computeIfAbsent(currentWord, Word::new);
                        if(topHundredWordSet.contains(wordInMap)){
                            topHundredWordSet.remove(wordInMap);
                            wordInMap.incrementCount();
                            topHundredWordSet.add(wordInMap);
                        } else{
                            wordInMap.incrementCount();
                            if(topHundredWordSet.isEmpty()||topHundredWordSet.size()<=100) {
                                topHundredWordSet.add(wordInMap);
                            } else {
                                if (topHundredWordSet.last().compareTo(wordInMap) >= 0) {
                                    topHundredWordSet.add(wordInMap);
                                    topHundredWordSet.pollLast();
                                }
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();
        } else
            throw new FileNotFoundException();
    }

    public Map<String, Word> getAllWordMap() {
        return allWordMap;
    }

    public TreeSet<Word> getTopHundredWordSet() {
        return topHundredWordSet;
    }
}
