package homework_10_30_11;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pavel on 04.12.2018.
 */
public class TableHandler {
    private File file;

    public TableHandler(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        this.file = file;
    }

    public void handleCommand(String command) throws TableException, IOException {
        Pattern commPattern = Pattern.compile("^-\\w");
        Matcher commMatcher = commPattern.matcher(command);
        if(commMatcher.find()){
            String comm = commMatcher.group();
            String commText = command.substring(2).trim();
            switch(comm){
                case "-u":
                    Pattern idPattern = Pattern.compile("^\\d+");
                    Matcher idMatcher = idPattern.matcher(commText);
                    if(idMatcher.find()){
                        replaceElement(commText);
                    } else
                        throw new TableException("Wrong format");
                    break;
                case "-d":
                    try{
                        int id = Integer.parseInt(commText);
                        deleteElement(id);
                    } catch (Exception ex){
                        throw new TableException("Wrong id format",ex);
                    }
                    break;
                case "-c":
                    addElement(commText);
                    break;
                default:
                    throw new TableException("Unknown command");

            }
        } else throw new TableException("Not a command");
    }

    private void replaceElement(String elementText) throws TableException, IOException {
        TableElement element = stringToElement(elementText);
        TreeMap<Integer,TableElement> elementMap = getAllElementsMap();
        if(elementMap.containsKey(element.getId())){
            elementMap.put(element.getId(),element);
        } else
            throw new TableException("Element doesn't exist");
    }

    private void addElement(String element) throws IOException, TableException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))){
            TableElement lastElement = this.getLastElement();
            int id = (lastElement==null)?0:lastElement.getId()+1;
            element =id+" "+element;
            TableElement newElement = stringToElement(element);
            writer.write(newElement.toString());
            writer.newLine();
        }
    }

    private void deleteElement(int elementId) throws IOException, TableException {
        TreeMap<Integer,TableElement> elementsMap = getAllElementsMap();
        if(elementsMap.containsKey(elementId)){
            elementsMap.remove(elementId);
            rewriteAllElements(elementsMap);
        }else{
            throw new TableException("Element not found");
        }

    }
    private void rewriteAllElements(TreeMap<Integer,TableElement> elementsMap) throws IOException {
        try(
                BufferedWriter writer = new BufferedWriter(new FileWriter(file,false))
        ){
            for(Map.Entry<Integer,TableElement> element:elementsMap.entrySet()){
                writer.write(element.getValue().toString());
            }
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
        } catch (Exception e) {
            throw new TableException("Wrong format",e);
        }
    }

    private TreeMap<Integer,TableElement> getAllElementsMap() throws IOException, TableException {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            TreeMap<Integer, TableElement> elementsMap = new TreeMap<>();
            String currentLine;
            while((currentLine = reader.readLine())!=null){
                currentLine = currentLine.trim();
                if(currentLine.length()>=53) {
                    TableElement currentElement = stringToElement(currentLine);
                    elementsMap.put(currentElement.getId(), currentElement);
                }
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
