package homework_4_29_10.FileHandler;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {
    public String readFile(String filePath){
        Pattern pattern = Pattern.compile("\\.\\w*");
        Matcher matcher = pattern.matcher(filePath);
        String fileType;
        if(matcher.find()) {
            fileType = matcher.group().replace(".", "");
            String output = "Все очень плохо";
            switch(fileType){
                case "xml":
                    output = XmlHandler.readXml(filePath);
                    break;
                case "txt":
                    output = TxtHandler.readTxt(filePath);
                    break;
            }
            return output;
        }else
            return null;

    }
}
