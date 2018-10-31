package lesson_7_31_10;

import java.util.Objects;
import java.util.Random;

public class Book {

    final String CONST;//не изменяемая переменная константа

    private String title;
    private String author;
    int pages;

    // final методом - его нельзя перезаписать
    // final класс - от него нельзя наследоваться
    // final в аргументе - аргумент нельзя изменять


    //clone() - возвращает копию объекта

    //getClass() - ссылка на класс

    //finalize() - вызывается при уничтожении сборщиком мусора

    // wait() notify() notifyAll()

    //equals() - сравнение объектов

    //hashcode() - возвращает hashcode объекта

    public static int soldBook;

    static{//Статически блок для инициализации статич объектов выполняется один раз когда класс появляется в памяти(сначала программы)
        soldBook = -(new Random().nextInt(100));
    }
    public void sellBook(){
        soldBook++;
    }

    public static String getStaticstic(){
        return "Book sold: "+soldBook;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null||this.getClass()!=obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(this.title,book.getTitle()) &&
                Objects.equals(this.author, book.getAuthor()) &&
                this.pages == book.getPages();
    }

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
        this.CONST = "String";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                '}';
    }
}
