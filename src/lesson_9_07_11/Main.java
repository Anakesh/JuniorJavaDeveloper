package lesson_9_07_11;

import lesson_9_07_11.classes.Book;
import lesson_9_07_11.classes.Car;
import lesson_9_07_11.classes.ChildBook;
import lesson_9_07_11.storage.Storage;

public class Main {

    public static void main(String[] args) {
        Book tails = new Book("Сказки", 500);
        Book flowers = new Book("Цветы", 600);

        Car car = new Car("green", 1000);

        Storage<Book> bookStorage = new Storage<>();
        bookStorage.add(tails);
        bookStorage.add(flowers);
        //bookStorage.add(car);

        // ClassCastException возникает на моменте исполнения
        Book bookFromStorage = bookStorage.get(2);
        System.out.println(bookFromStorage);

        Storage<ChildBook> childBookStrorage = new Storage<>();

        ChildBook bears = new ChildBook("Masha i medved",200,"pic");
        ChildBook coloring = new ChildBook("Coloring",100,"pic");

        childBookStrorage.add(bears);

        Book firstBook = getFirstBookAgain(bookStorage);
        Book secondBook = getFirstBookAgain(childBookStrorage);

    }
//    public static Book getFirstBook(Storage<Book> storage){
//        return storage.get(0);
//    }

//    public void somVoid(Storage<String> storage){}
//    public void somVoid(Storage<Integer> storage){}



    public static Book getFirstBookAgain(Storage<? extends Book> storage){
        return storage.get(0);
    }

    public static void addToStorage(Storage<? super Book> storage){
        Book book = new Book("book",1000);
        storage.add(book);

//        Book b = storage.get(0);
        Object b = storage.get(0);
        System.out.println(b);
    }

    public static void storage(Storage<?>storage){
        Book book = new Book("book",10);
//        storage.add(book);
//        Book book1 = storage.get(0);
        storage.add(null);
        Object obj = storage.get(0);
        System.out.println(obj);
    }
}
