package homework_11_05_12_patterns.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Pavel on 11.12.2018.
 */
public class FileLogger implements ILogger {
    @Override
    public void write(String info) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("log.log",true))){
            writer.write("Log info: "+ info);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
