package homework_3_26_10.task2_26_10;

/**
 * Created by Pavel on 27.10.2018.
 */
public class NodeListElement extends Node {
    private NodeListElement nextNode;
    private int index;

    public NodeListElement(int value){super(value);}
    public NodeListElement(Node node){
        this.value = node.getValue();
    }

    public NodeListElement getNextNode() {
        return nextNode;
    }

    public void setNextNode(NodeListElement nextNode) {
        this.nextNode = nextNode;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
