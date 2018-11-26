package homework_6_12_11.Collections.Task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortedLists {

    private Map<String,List<Integer>> lists;
    private List<Integer> baseList;
    private List<Integer> listByTwo;
    private List<Integer> listByThree;

    public SortedLists(List<Integer> baseList) {
        this.lists = new HashMap<>();
        this.baseList = baseList;
        listByTwo = new ArrayList<>();
        listByThree = new ArrayList<>();
        for (Integer integer : baseList) {
            if(integer%2==0)
                listByTwo.add(integer);
            if (integer % 3 == 0)
                listByThree.add(integer);
        }
        lists.put("BaseList",baseList);
        lists.put("ListByTwo",listByTwo);
        lists.put("ListByThree",listByThree);

    }
    public void printList(){
        printList("BaseList");
        printList("ListByTwo");
        printList("ListByThree");
    }
    public void printList(String name){
        System.out.print(name+": ");
        List<Integer> list = lists.get(name);
        for (Integer integer:list)
            System.out.print(integer+", ");
        System.out.println();
    }

    public List<Integer> getBaseList() {
        return baseList;
    }

    public List<Integer> getListByTwo() {
        return listByTwo;
    }

    public List<Integer> getListByThree() {
        return listByThree;
    }
}
