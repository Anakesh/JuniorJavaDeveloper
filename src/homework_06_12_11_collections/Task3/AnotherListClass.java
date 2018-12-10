package homework_06_12_11_collections.Task3;

import java.util.ArrayList;
import java.util.List;

public class AnotherListClass<T> {
    private List<T> list;

    public AnotherListClass(List<T> list) {
        this.list = list;
    }
    public void doubleValue(){
        List<T> doubleList = new ArrayList<>();
        for(T element : list){
            doubleList.add(element);
            doubleList.add(element);
        }
        list = doubleList;
    }
    public void printList(){
        for (T element:list)
            System.out.println(element);
    }
}
