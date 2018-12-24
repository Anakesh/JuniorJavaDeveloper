package lesson_26_24_12_logger.logger;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.logging.*;

public class Log {
    private static final Logger LOGGER = Logger.getLogger(Log.class.getName());

    static{
        //Установка уровня важности
        //fine->finer->finest->config->info->
        LOGGER.setLevel(Level.WARNING); //Warning+errors
        LOGGER.setLevel(Level.INFO);    //Warning+errors+info
        LOGGER.setLevel(Level.ALL);
        LOGGER.setLevel(Level.SEVERE); // Errors
    }

    public static void main(String[] args) {
        LOGGER.info("Started with args: "+
                Arrays.toString(args));
        try{
            int result = 3/0;
        }catch (Exception ex){
            LOGGER.severe("Fatal error: "+ ex.getMessage());
        }
    }
}

class FileLog {
    private static final Logger FILELOGGER = Logger.getLogger(FileLog.class.getName());
    static{
        try{
            FILELOGGER.addHandler(new FileHandler("logged.log.xml"));
            FileHandler fileHandler = new FileHandler("logged.log");
            fileHandler.setFormatter(new MyFormatter());
            FILELOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            FILELOGGER.warning("FAILED");
        }
    }
    public static void main(String[] args) {
        FILELOGGER.info("Started with args: "+
                Arrays.toString(args));
        try{
            int result = 3/0;
        }catch (Exception ex){
            FILELOGGER.severe("Fatal error: "+ ex.getMessage());
        }
    }
}

class MyFormatter extends Formatter {
    private final static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue(){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    @Override
    public String format(LogRecord record) {
        StringBuilder message = new StringBuilder();
        message.append(formatTime(record.getMillis()));
        message.append(" Level: ").append(record.getLevel().toString());
        message.append(" Message: ").append(record.getMessage()).append('\n');
        return message.toString();
    }

    public String formatTime(long mls){
        return dateFormat.get().format(new Date(mls));
    }
}