package homework_6_12_11.Collections.Task3;

import java.util.ArrayList;
import java.util.List;

public class AnotherListClaa<T> {
    private List<T> list;

    public AnotherListClaa(List<T> list) {
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
