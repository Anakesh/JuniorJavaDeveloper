package homework_4_29_10.FileHandler;

abstract class FileHandler {

    String path;
    String name;

    public FileHandler(String path) {
        this.path = path;
        String[] str =  path.split(".");
        this.name = str[str.length-1];
    }

    abstract String readFile();
    abstract void writeFile(String text);
}
