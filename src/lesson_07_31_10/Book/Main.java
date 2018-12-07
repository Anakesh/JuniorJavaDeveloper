package lesson_07_31_10.Book;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("Война и Мир", "Толстой");
        book1.setPages(1000);
        Book book2 = new Book("Война и Мир", "Толстой");
        book2.setPages(1000);

        System.out.println(book1.toString());
        System.out.println(book2.toString());

        System.out.println(book1==book2);//сравнение ссылок true если ссыдаются на один и тот же объект
        System.out.println(book1.equals(book2));

        System.out.println(Book.getStaticstic());
        book1.sellBook();
        book1.sellBook();
        System.out.println(Book.getStaticstic());

        book2.sellBook();
        System.out.println(Book.getStaticstic());
    }
}
