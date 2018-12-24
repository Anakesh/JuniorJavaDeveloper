package lesson_26_24_12_logger.logger;

import org.apache.log4j.Logger;

import java.util.Arrays;

public class Log4jLoggedLProgram {
    //Log4j
    private static final Logger LOGGER = Logger.getLogger(Log4jLoggedLProgram.class);
    /*
    Уровни:
    FATAL
    ERROR
    WARN
    INFO
     */
    public static void main(String[] args) {
        LOGGER.info("Started with args: "+
                Arrays.toString(args));
        try{
            int result = 3/0;
        }catch (Exception ex){
            LOGGER.error("Fatal error: "+ ex.getMessage());
        }
    }
}

