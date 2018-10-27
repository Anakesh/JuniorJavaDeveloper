package homework_3_26_10.task2_26_10;

public class Node {
    int value;
    int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    Node nextNode;
    Node previousNode;
    public Node(int value){
        this.value = value;
    }
    public Node(int value, Node nextNode,Node previousNode){
        this.value= value;
        this.nextNode = nextNode;
        this.previousNode = previousNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }
}
