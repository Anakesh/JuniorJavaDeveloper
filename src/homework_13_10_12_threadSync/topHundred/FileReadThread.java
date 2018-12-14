package homework_13_10_12_threadSync.topHundred;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileReadThread extends Thread {
    private File file ;
    private long startPos;
    private long length;
    private RandomAccessFile raf;

    public Map<String, Integer> getWordMap() {
        return wordMap;
    }

    private Map<String,Integer> wordMap = new HashMap<>();

    public FileReadThread(File file){
        this.file = file;
        this.startPos = 0;
        this.length = file.length();
    }

    public FileReadThread(File file, long startPos, long length) {
        this.file = file;
        this.startPos = startPos;
        this.length = length;
    }

    @Override
    public void run() {
        try(RandomAccessFile raf = new RandomAccessFile(file,"r");) {
            raf.seek(startPos);
//            raf.setLength(startPos+length);
            try(BufferedReader bis = new BufferedReader(new FileReader(raf.getFD()))){
                String line;
                while((line = bis.readLine())!=null&&raf.getFilePointer()<(startPos+length)){
                    String[] words = line.toLowerCase().replaceAll("'","").replaceAll("[\\W\\d]"," ").split("\\s+");
                    for(String word:words){
                        if(!word.equals("")) {
                            Integer num = wordMap.get(word);
                            wordMap.put(word, (num == null) ? 1 : num + 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
