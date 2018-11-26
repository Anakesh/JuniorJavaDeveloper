package homework_8_21_11;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;


public class FileHandler {

    public void copyFile(File sourceFile, File targetFile) throws IOException {
        try(
                InputStream sourceInStr = new FileInputStream(sourceFile);
                BufferedInputStream sourceBufInStr = new BufferedInputStream(sourceInStr);
                OutputStream targetOutStr = new FileOutputStream(targetFile);
                BufferedOutputStream targetBufOutStr = new BufferedOutputStream(targetOutStr);
        ){
            int b;
            while((b = sourceBufInStr.read())>-1){
                targetBufOutStr.write(b);
            }
        }
    }

    public void splitFileByByteLength(File sourceFile, long lengthOfNewFiles) throws IOException {
        int numOfFiles;
        if (sourceFile.length() % lengthOfNewFiles == 0)
            numOfFiles = (int) (sourceFile.length() / lengthOfNewFiles);
        else
            numOfFiles = (int) (sourceFile.length() / lengthOfNewFiles + 1);
        MyBufferedOutputStream[] buffOutStreams = new MyBufferedOutputStream[numOfFiles];
        String[] fileName = sourceFile.getName().split("\\.");
        for(int i = 0;i<buffOutStreams.length;i++) {
            buffOutStreams[i] = new MyBufferedOutputStream(fileName[0]+ "_" +"part" + (i+1) +  fileName[1]);
        }
        try(
                MyBufferedInputStream buffInStr = new MyBufferedInputStream(sourceFile)
        ){
            int currFileNum = 0;
            long currFileLength = 0;
            int b;
            while((b=buffInStr.read())>0){
                buffOutStreams[currFileNum].write(b);
                currFileLength++;
                if(currFileLength>=lengthOfNewFiles){
                    currFileNum++;
                    currFileLength = 0;
                }
            }
        }
        for(int i = 0;i<buffOutStreams.length;i++)
            buffOutStreams[i].close();
    }

    public void combineFiles(File[] files, String newFilePath) throws IOException {
        Vector<InputStream> inVector = new Vector<>();
        for(int i = 0;i<files.length;i++)
            inVector.add(new FileInputStream(files[i]));
        SequenceInputStream seqStream = new SequenceInputStream(inVector.elements());
        try(MyBufferedOutputStream buffOutStr = new MyBufferedOutputStream(newFilePath)){
            byte[] buf = new byte[1024];
            int len;

            while ((len = seqStream.read(buf)) > 0) {
                buffOutStr.write(buf, 0, len);
            }
        }
    }




}
