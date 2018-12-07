package homework_03_26_10.task2_26_10;

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

        nodeList.set(nodeList.size()-1,new Node(10));
        System.out.println(nodeList.toString());

        nodeList.add(new Node(5));
        System.out.println(nodeList.toString());

        NodeList anotherNodeList = nodeList.find(5);
        Node firstOfAnother = anotherNodeList.first();
        firstOfAnother.setValue(10);
        System.out.println(anotherNodeList.toString());

        nodeList.add(new Node(6));
        nodeList.add(new Node(3));
        nodeList.add(new Node(1));
        System.out.println(nodeList.toString());

        nodeList.removeByValue(5);
        System.out.println(nodeList.toString());

        nodeList.removeByIndex(3);
        System.out.println(nodeList.toString());

        nodeList.removeByIndex(0);
        System.out.println(nodeList.toString());
    }
}
