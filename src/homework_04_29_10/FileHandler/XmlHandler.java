package homework_04_29_10.FileHandler;

public class XmlHandler extends FileHandler{
    public XmlHandler(String path) {
        super(path);
    }


    @Override
    String readFile() {
        return "Xml файл по пути: "+this.path+" прочтен";
    }

    @Override
    void writeFile(String text) {
        System.out.println("Xml файл по пути: "+path+" прочтен");
    }
}
