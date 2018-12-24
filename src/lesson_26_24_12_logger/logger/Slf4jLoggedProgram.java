package lesson_26_24_12_logger.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

//slf4j
class Slf4jLoggedProgram{
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jLoggedProgram.class);
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
