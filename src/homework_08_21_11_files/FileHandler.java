package homework_08_21_11_files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FileHandler {

    public void copyFile(File sourceFile, File targetFile) throws IOException {
        try (
                BufferedInputStream sourceBufInStr = getBuffInStr(sourceFile);
                BufferedOutputStream targetBufOutStr = getBuffOutStr(targetFile);
        ) {
            int b;
            while ((b = sourceBufInStr.read()) > -1) {
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
        BufferedOutputStream[] buffOutStreams = new BufferedOutputStream[numOfFiles];
        String[] fileName = sourceFile.getName().split("(\\.(?!(\\w+\\.\\w*)+))");
        for (int i = 0; i < buffOutStreams.length; i++) {
            buffOutStreams[i] = getBuffOutStr(new File(fileName[0] + "_" + "part" + (i + 1) + "." + fileName[1]));
        }
        try (BufferedInputStream buffInStr = getBuffInStr(sourceFile)) {
            int currFileNum = 0;
            long currFileLength = 0;
            int b;
            while ((b = buffInStr.read()) > -1) {
                buffOutStreams[currFileNum].write(b);
                currFileLength++;
                if (currFileLength >= lengthOfNewFiles) {
                    currFileNum++;
                    currFileLength = 0;
                }
            }
        }
        for (BufferedOutputStream buffOutStream : buffOutStreams)
            buffOutStream.close();
    }

    public void splitFileByPercent(File file, double percent) throws IOException {
        long fileLength = file.length();
        if (fileLength % (100d / percent) > 0)
            this.splitFileByByteLength(file, (long) ((fileLength / (100 / percent)) + 1));
        else
            this.splitFileByByteLength(file, (long) (fileLength / (100 / percent)));

    }

    public void combineFiles(File[] files, String newFilePath) throws IOException {
        Vector<InputStream> inVector = new Vector<>();
        for (File file : files)
            inVector.add(new FileInputStream(file));
        SequenceInputStream seqStream = new SequenceInputStream(inVector.elements());
        try (BufferedOutputStream buffOutStr = getBuffOutStr(new File(newFilePath))) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = seqStream.read(buf)) > -1) {
                buffOutStr.write(buf, 0, len);
            }
        }
    }

    public void doXorWithKey(File file, File newFile, byte[] key) throws IOException {
        try (
                BufferedInputStream bufInStr = getBuffInStr(file);
                BufferedOutputStream bufOutStr = getBuffOutStr(newFile)
        ) {
            byte b;
            for (int i = 0; (b = (byte) bufInStr.read()) > -1; i++) {
                bufOutStr.write((int) b ^ key[i % key.length]);
            }
        }
    }

    public void doXorWithFile(File targetFile, File keyFile, File newFile) throws IOException {
        try (
                BufferedInputStream targetBufInStr = getBuffInStr(targetFile);
                BufferedOutputStream bufOutStr = getBuffOutStr(newFile)
        ) {
            BufferedInputStream keyBufInStr = getBuffInStr(keyFile);
            byte targetByte;
            byte keyByte;
            while ((targetByte = (byte) targetBufInStr.read()) > -1) {
                if ((keyByte = (byte) keyBufInStr.read()) <= 0) {
                    keyBufInStr = getBuffInStr(keyFile);
                    keyByte = (byte) keyBufInStr.read();
                }
                bufOutStr.write(targetByte ^ keyByte);
            }
            keyBufInStr.close();
        }
    }

    public List<Byte> fileToByteList(File file) throws IOException {
        try (BufferedInputStream bufInStr = getBuffInStr(file)) {
            byte b;
            List<Byte> byteList = new ArrayList<>();
            while ((b = (byte) bufInStr.read()) > -1) {
                byteList.add(b);
            }
            return byteList;
        }
    }

    public void filterByWordLength(File targetFile, File newFile, int lowerRange, int higherRange) throws IOException {
        try (
                BufferedReader buffReader = new BufferedReader(new FileReader(targetFile));
                BufferedWriter buffWriter = new BufferedWriter(new FileWriter(newFile))
        ) {
            String currentLine;
            while ((currentLine = buffReader.readLine()) != null) {
                String[] words = currentLine.trim().split("\\s+");
                for (String word : words)
                    if (word.length() < lowerRange || word.length() > higherRange)
                        buffWriter.write(word + " ");
                buffWriter.write("\n");
            }
        }
    }

    public void writeDirectoryFilesInfo(File folder, File file) throws IOException {
        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file))) {
            for (File fileEntry : folder.listFiles()) {
                Path path = Paths.get(fileEntry.getAbsolutePath());
                FileTime time = Files.readAttributes(path, BasicFileAttributes.class).creationTime();
                buffWriter.write(path.toString() + '\t' + fileEntry.getName() + '\t' + time.toString() + '\n');
            }
            buffWriter.flush();
        }
    }

    public void copyAllFilesInDirectory(File sourceFolder, File destinationFolder) throws IOException {
        for (File file : sourceFolder.listFiles()) {
            if(!file.isDirectory()){
                File fileCopy = new File(Paths.get(destinationFolder.getAbsolutePath(), file.getName()).toString());
                this.copyFile(file, fileCopy);
            }
        }
    }

    private BufferedInputStream getBuffInStr(File file) throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(file));
    }
    private BufferedOutputStream getBuffOutStr(File file) throws FileNotFoundException {
        return new BufferedOutputStream(new FileOutputStream(file));
    }
}
