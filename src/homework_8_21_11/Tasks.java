package homework_8_21_11;

import java.io.File;
import java.io.IOException;

public class Tasks {
    public long doTaskOne(String firstFilePath, String secondFilePath) throws IOException {
        File file1 = new File(firstFilePath);
        File file2 = new File(secondFilePath);
        FileHandler handler = new FileHandler();
        if(file1.exists()){
            handler.copyFile(file1,file2);
            return file1.length();
        }
        else {
            throw new IOException("First file doesn't exist");
        }
    }
    public void doTaskTwoSplit(String filePath, long lengthOfNewFiles) throws IOException {
        File file = new File(filePath);
        FileHandler handler = new FileHandler();
        if(file.exists()){
            handler.splitFileByByteLength(file,lengthOfNewFiles);
        }
        else {
            throw new IOException("File doesn't exist");
        }
    }
    public void doTaskTwoCombine(String[] fileNames, String newFilePath) throws IOException {
        File[] files = new File[fileNames.length];
        FileHandler handler = new FileHandler();
        for(int i = 0;i<files.length;i++) {
            files[i] = new File(fileNames[i]);
            if(!files[i].exists())
                throw new IOException("File number "+(i+1)+"doesn't exist");
        }
        handler.combineFiles(files,newFilePath);
    }
}
