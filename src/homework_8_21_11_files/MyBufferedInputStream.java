package homework_8_21_11_files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyBufferedInputStream extends BufferedInputStream {
    public MyBufferedInputStream(File file) throws FileNotFoundException {
        super(new FileInputStream(file));
    }
}
