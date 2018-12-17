package homework_14_14_12_safeColl.blockingQueueTop;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class FileReaderThread extends Thread {
    private File file ;
    private long startPos;
    private long length;
    private BlockingQueue<String> wordQueue;

    public FileReaderThread(File file, BlockingQueue<String> wordQueue){
        this.file = file;
        this.startPos = 0;
        this.length = file.length();
        this.wordQueue = wordQueue;
    }

    public FileReaderThread(File file, long startPos, long length, BlockingQueue<String> wordQueue) {
        this.file = file;
        this.startPos = startPos;
        this.length = length;
        this.wordQueue = wordQueue;
    }

    @Override
    public void run() {
        try(RandomAccessFile raf = new RandomAccessFile(file,"r");) {
            raf.seek(startPos);
            try(BufferedReader bis = new BufferedReader(new FileReader(raf.getFD()))){
                String line;
                while((line = bis.readLine())!=null&&raf.getFilePointer()<(startPos+length)){
                    String[] words = line.toLowerCase().replaceAll("'","")
                            .replaceAll("[\\W\\d]"," ").trim().split("\\s+");
                    for(String word:words){
                        if(!word.equals("")) {
                            wordQueue.put(word);
                        }
                    }
                }
                wordQueue.put("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
