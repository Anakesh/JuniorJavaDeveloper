package lesson_17_03_12_patterns.singletorn;

import java.io.*;
import java.util.Properties;

public class AppConfig {
    private static final File CONFIG = new File("app.properties");
    private static AppConfig instance;
    private final Properties properties;

    private AppConfig(){
        properties = new Properties();
        try{
            properties.load(new FileInputStream(CONFIG));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppConfig getConfig(){
        if(instance == null){
            instance = new AppConfig();
        }
        return instance;
    }

    public String getParam(String key){
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        AppConfig config = AppConfig.getConfig();
        System.out.println(config.getParam("db.login"));
    }
}
