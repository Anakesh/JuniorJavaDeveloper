package homework_8_21_11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MoreTasks {
    public void doTaskOne(String filePath, String newDirectoryName, int lowerRange, int higherRange) throws IOException {
        if (lowerRange > higherRange)
            throw new IOException("Wrong range");
        File file = new File(filePath);
        if (file.exists()) {
            Path newDirectoryPath = Paths.get(newDirectoryName);
            if (!Files.exists(newDirectoryPath))
                Files.createDirectories(newDirectoryPath);
            String[] fileName = filePath.split("(\\.(?!(\\w+\\.\\w*)+))");
            File newFile = new File(Paths.get(newDirectoryName, (fileName[0] + "_" + "filtered" + "." + fileName[1])).toString());
            FileHandler handler = new FileHandler();
            handler.filterByWordLength(file, newFile, lowerRange, higherRange);

        } else throw new IOException("File doesn't exist");
    }

    public void doTaskTwo(String directoryPath) throws IOException {
        File folder = new File(directoryPath);
        if (folder.exists() && folder.isDirectory() && folder.listFiles() != null) {
            Path path = Paths.get(folder.getAbsolutePath(), (folder.getName() + "_listOfFiles.txt"));
            File file = new File(path.toString());
            FileHandler handler = new FileHandler();
            handler.writeDirectoryFilesInfo(folder, file);
        } else throw new IOException("Folder doesn't exist or empty");
    }

    public void doTaskThree(String sourceFolderName, String destinationFolderName) throws IOException {
        File sourceFolder = new File(sourceFolderName);
        if (sourceFolder.exists() && sourceFolder.isDirectory() && sourceFolder.listFiles() != null) {
            Path destinationFolderPath = Paths.get(destinationFolderName);
            if (!Files.exists(destinationFolderPath))
                Files.createDirectories(destinationFolderPath);
            File destinationFolder = new File(destinationFolderName);
            FileHandler handler = new FileHandler();
            handler.copyAllFilesInDirectory(sourceFolder, destinationFolder);
        } else throw new IOException("Folder doesn't exist or empty");
    }
}
