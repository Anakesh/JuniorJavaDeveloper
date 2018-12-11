package homework_11_05_12_patterns.Logger;

/**
 * Created by Pavel on 11.12.2018.
 */
public class LoggerContext {
    private ILogger logger;
    public LoggerContext(){}
    public LoggerContext(ILogger logger){
        this.logger = logger;
    }

    public void setLogger(ILogger logger) {
        this.logger = logger;
    }

    public void write(String info){
        logger.write(info);
    }
}
