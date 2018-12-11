package homework_11_05_12_patterns.Logger;

public class Main {
    public static void main(String[] args) {
        LoggerContext loggerContext = new LoggerContext();
        loggerContext.setLogger(new ConsoleLogger());
        loggerContext.write("Console log");
        loggerContext.setLogger(new FileLogger());
        loggerContext.write("File log");
        loggerContext.setLogger(new TimeFileLogger());
        loggerContext.write("Time file log");
    }
}

