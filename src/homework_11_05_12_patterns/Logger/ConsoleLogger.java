package homework_11_05_12_patterns.Logger;

/**
 * Created by Pavel on 11.12.2018.
 */
public class ConsoleLogger implements ILogger {
    @Override
    public void write(String info) {
        System.out.println(info);
    }
}
