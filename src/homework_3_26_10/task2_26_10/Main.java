package homework_3_26_10.task2_26_10;

public class Main {
    public static void main(String[] args) {
        NodeList nodeList = new NodeList();
        nodeList.add(new Node(5));
        nodeList.add(new Node(6));
        nodeList.add(new Node(6));
        nodeList.add(new Node(7));
        System.out.println(nodeList.toString());
        nodeList.removeByValue(6);
        System.out.println(nodeList.toString());
        nodeList.removeByValue(7);
        System.out.println(nodeList.toString());
        nodeList.add(new Node(9));
        nodeList.add(new Node(6));
        nodeList.add(new Node(3));
        nodeList.add(new Node(1));
        System.out.println(nodeList.toString());
        nodeList.removeByIndex(3);
        System.out.println(nodeList.toString());
    }
}
