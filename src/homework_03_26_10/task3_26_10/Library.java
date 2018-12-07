package homework_03_26_10.task3_26_10;

import java.util.HashMap;
import java.util.Map;

public class Library {
    private static Map<String,Integer> bookStorage = new HashMap<>();

    public void put(Book book,int quantity){
        if(quantity>0) {
            Integer quantityInLib = bookStorage.get(book.getName());
            bookStorage.put(book.getName(), (quantityInLib == null) ? quantity : quantityInLib + quantity);
        }
        else{
            System.out.println("Указано неверное количество книг");
        }
    }

    public void get(Book book,int quantity){
        Integer quantityInLib = bookStorage.get(book.getName());
        if(quantityInLib==null)
            System.out.println("Такой книги нет.");
        else if(quantityInLib<quantity)
            System.out.println("В библиотеке недостаточно книг \""+book.getName()+"\". Требуется: "+quantity+" Имеется: "+quantityInLib);
        else {
            bookStorage.put(book.getName(), quantityInLib - quantity);
            System.out.println("Осталось " + (quantityInLib - quantity) + " книг.");
        }
    }
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        for(String bookNam :bookStorage.keySet()){
            string.append("Название книги: ").append(bookNam).append('\t').append("Количество: ").append(bookStorage.get(bookNam)).append('\n');
        }
        return string.toString();
    }
}
