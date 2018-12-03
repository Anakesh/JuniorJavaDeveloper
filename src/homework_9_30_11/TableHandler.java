package homework_9_30_11;

import java.io.*;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableHandler {
    private File file;

    public TableHandler(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        this.file = file;
    }
    public void handleCommand(String command) throws TableException {
        Pattern commPattern = Pattern.compile("^-\\w");
        Matcher commMatcher = commPattern.matcher(command);
        if(commMatcher.find()){
            String comm = commMatcher.group();
            switch(comm){
                case "-u":

                    break;
                case "-d":
                    break;
                case "-c":
                    break;
            }
        } else throw new TableException("Wrong command");
    }

    private void addElement(String element) throws IOException, TableException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))){
            TableElement lastElement = this.getLastElement();
            int id = (lastElement==null)?0:lastElement.getId()+1;
            element =id+" "+element;
            TableElement newElement = stringToElement(element);
            writer.write(element+'\n');
        }
    }

    private TableElement stringToElement(String str) throws TableException {
        if(str == null) return null;
        try {
            String[] elements = str.trim().split("\\s+");
            int id = Integer.parseInt(elements[0]);
            StringBuilder name = new StringBuilder();
            for (int i = 1; i < elements.length - 2; i++)
                name.append(elements[i]).append(" ");
            double price = Double.parseDouble(elements[elements.length - 2]);
            int quantity = Integer.parseInt(elements[elements.length - 1]);
            return new TableElement(id, name.toString().trim(), price, quantity);
        } catch (Exception e) {throw new TableException("Wrong format",e);}
    }
    private Map<Integer,TableElement> getAllElementsMap() throws IOException, TableException {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            Map<Integer, TableElement> elementsMap = new HashMap<>();
            String currentLine;
            while((currentLine = reader.readLine())!=null){
                TableElement currentElement = stringToElement(currentLine);
                elementsMap.put(currentElement.getId(),currentElement);
            }

            return elementsMap;
        }
    }
    private TableElement getLastElement() throws IOException, TableException {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String lastLine = null;
            String currentLine;
            while((currentLine = reader.readLine())!=null)
                lastLine = currentLine;
            return this.stringToElement(lastLine);
        }
    }
}
