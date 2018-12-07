package homework_08_21_11_files;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MyBufferedOutputStream extends BufferedOutputStream {
    public MyBufferedOutputStream(File file) throws FileNotFoundException {
        super(new FileOutputStream(file));
    }
    public MyBufferedOutputStream(String fileName) throws FileNotFoundException  {
        super(new FileOutputStream(fileName));
    }
}
