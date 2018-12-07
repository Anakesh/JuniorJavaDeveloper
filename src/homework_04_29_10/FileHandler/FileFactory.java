package homework_04_29_10.FileHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFactory {
    public FileHandler handleFile(String filePath){
        Pattern pattern = Pattern.compile("\\.\\w*");
        Matcher matcher = pattern.matcher(filePath);
        String fileType;
        if(matcher.find()) {
            fileType = matcher.group().replace(".", "");
            String output;
            FileHandler handler;
            switch(fileType){
                case "xml":
                    handler = new TxtHandler(filePath);
                    break;
                case "txt":
                    handler =  new XmlHandler(filePath);
                    break;
                default:
                    handler = null;
                    break;
            }
            return handler;
        }else
            return null;

    }
}
