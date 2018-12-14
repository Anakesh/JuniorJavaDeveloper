package homework_11_05_12_patterns.Logger;

import java.util.Date;

/**
 * Created by Pavel on 11.12.2018.
 */
public class   TimeFileLogger implements ILogger {
    @Override
    public void write(String info) {
        Date date = new Date();
        info += ", Time: "+ date.toString();
        FileLogger fileLogger = new FileLogger();
        fileLogger.write(info);
    }
}
