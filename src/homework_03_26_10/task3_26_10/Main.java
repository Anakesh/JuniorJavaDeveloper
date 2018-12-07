package homework_03_26_10.task3_26_10;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        Book warAndPeace = new Book("War and peace");
        Book fool = new Book("Fool");
        lib.put(warAndPeace ,100);
        lib.put(fool,1);
        lib.get(fool,2);
        lib.get(warAndPeace,50);
        System.out.println(lib.toString());
    }
}
