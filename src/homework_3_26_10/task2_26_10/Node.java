package homework_3_26_10.task2_26_10;

public class Node {
    protected int value;

    public Node(){}
    public Node(int value){
        this.value = value;
    }

    public Node CopyOf(Node node){
        Node copyNode = new Node();
        copyNode.setValue(node.getValue());
        return copyNode;
    }

    @Override
    public String toString(){
        return "Value = " +value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
