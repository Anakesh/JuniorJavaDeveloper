package homework_4_29_10;

import homework_4_29_10.FileHandler.FileHandler;
import homework_4_29_10.MyList.MyList;

public class Main {

    public static void main(String[] args) {
        // write your code here
        MyList<Integer> nodeMyList = new MyList<>();
        nodeMyList.add(1,2,3,4,5,6,7);
        System.out.println(nodeMyList.toString());
        nodeMyList.reverseList();
        System.out.println(nodeMyList.toString());
        nodeMyList.remove(2);
        System.out.println(nodeMyList.toString());
        nodeMyList.putAfter(1,5);
        System.out.println(nodeMyList.toString());
        nodeMyList.reverseList();
        nodeMyList.shift(0);
        nodeMyList.push(8);
        System.out.println(nodeMyList.toString());
        nodeMyList.pop();
        nodeMyList.unshift();
        System.out.println(nodeMyList.toString());

        FileHandler fileHandler = new FileHandler();
        String filePath = "foo\\foo1\\foo2\\foo.txt";
        System.out.println(fileHandler.readFile(filePath));

    }
}
