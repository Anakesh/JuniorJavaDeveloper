package lesson_17_03_12_patterns.factory;

public class Factory {
    public static void main(String[] args) {
        Factory factory = new Factory();
        String file = "qwe.txt";
        Handler handler = factory.handle(file);
        handler.read();
        handler.write();
    }
    public Handler handle(String str){
        Handler handler = null;
        if(str.endsWith("txt")){
            handler = new TxtHandler();
        }else if(str.endsWith("xml")){
            handler = new XmlHandler();
        }
        return handler;
    }
}
abstract class Handler{
    abstract void read();
    abstract void write();
}
class TxtHandler extends Handler {

    @Override
    void read() {
        System.out.println("Read from txt.");
    }

    @Override
    void write() {
        System.out.println("Write to txt.");
    }
}
class XmlHandler extends Handler{

    @Override
    void read() {
        System.out.println("Read from xml.");
    }

    @Override
    void write() {
        System.out.println("Write to xml.");
    }
}
