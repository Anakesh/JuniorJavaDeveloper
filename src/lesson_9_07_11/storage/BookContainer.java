package lesson_9_07_11.storage;

import lesson_9_07_11.classes.Book;

public class BookContainer<T extends Book> {
    private T book;
    public BookContainer(T book){
        this.book = book;
    }
    public String getBookTitle(){
        return this.book.getName();
    }
    public static void main(String[]args){
        Book tales = new Book("Tales",500);
        BookContainer<Book> container = new BookContainer<>(tales);
        System.out.println(container.getBookTitle());


    }

}
