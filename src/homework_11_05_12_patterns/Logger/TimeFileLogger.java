package homework_11_05_12_patterns.Logger;

import java.util.Date;

/**
 * Created by Pavel on 11.12.2018.
 */
public class   TimeFileLogger extends FileLogger {
    @Override
    public void write(String info) {
        Date date = new Date();
        info += ", Time: "+ date.toString();
        super.write(info);
    }
}
