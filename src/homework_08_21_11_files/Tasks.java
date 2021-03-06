package homework_08_21_11_files;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

public class Tasks {
    private FileHandler handler = new FileHandler();
    public long doTaskOne(String firstFilePath, String secondFilePath) throws IOException {
        File file1 = new File(firstFilePath);
        File file2 = new File(secondFilePath);
        if (file1.exists()) {
            handler.copyFile(file1, file2);
            return file1.length();
        } else {
            throw new IOException("First file doesn't exist");
        }
    }

    public void doTaskTwoSplit(String filePath, long lengthOfNewFiles) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            handler.splitFileByByteLength(file, lengthOfNewFiles);
        } else {
            throw new IOException("File doesn't exist");
        }
    }

    public void doTaskTwoCombine(String[] filePaths, String newFilePath) throws IOException {
        File[] files = new File[filePaths.length];
        for (int i = 0; i < files.length; i++) {
            files[i] = new File(filePaths[i]);
            if (!files[i].exists())
                throw new IOException("File № " + (i + 1) + " doesn't exist");
        }
        handler.combineFiles(files, newFilePath);
    }

    public void doTaskThreeXorKey(String filePath, byte[] key) throws IOException {
        File file = new File(filePath);
        String[] fileName = filePath.split("(\\.(?!(\\w+\\.\\w*)+))");
        File newFile = new File(fileName[0] + "_" + "keyXORed" + "." + fileName[1]);
        if (file.exists()) {
            handler.doXorWithKey(file, newFile, key);
        } else {
            throw new IOException("File doesn't exist");
        }
    }

    public void doTaskThreeXorFile(String targetFilePath, String keyFilePath) throws IOException {
        File targetFile = new File(targetFilePath);
        String[] fileName = targetFilePath.split("(\\.(?!(\\w+\\.\\w*)+))");
        File keyFile = new File(keyFilePath);
        File newFile = new File(fileName[0] + "_" + "fileXORed" + "." + fileName[1]);
        if (targetFile.exists() && keyFile.exists() && keyFile.length() > 0) {
            handler.doXorWithFile(targetFile, keyFile, newFile);
        } else {
            throw new IOException("File doesn't exist");
        }
    }

    public TreeSet<Byte> doTaskFour(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            return new TreeSet<>(handler.fileToByteList(file));
        } else {
            throw new IOException("File doesn't exist");
        }
    }

    public int doTaskFive(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            List<Byte> byteList = handler.fileToByteList(file);
            byte comma = '\u002C';
            int numOfCommas = 0;
            for (Byte b : byteList)
                if (b.equals(comma))
                    numOfCommas++;
            return numOfCommas;
        } else {
            throw new IOException("File doesn't exist");
        }
    }

    public void doTaskSix(String filePath, int percent) throws IOException {
        File file = new File(filePath);
        if (file.exists() && percent <= 100) {
            handler.splitFileByPercent(file, percent);
        } else if (percent > 100) {
            throw new IOException("Wrong percent value");
        } else if (!file.exists()) {
            throw new IOException("File doesn't exist");
        }
    }
}
