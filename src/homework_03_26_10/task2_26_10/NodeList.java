package homework_03_26_10.task2_26_10;

public class NodeList {
    private NodeListElement first;
    private NodeListElement last;
    private int length = 0;

    public void add(Node node) {

        if (first != null) {
            last.setNextNode(new NodeListElement(node,last.getIndex()+1));
            last = last.getNextNode();
            length++;
        } else {
            first = new NodeListElement(node,0);
            last = first;
            length++;
        }
    }

    public void removeByValue(int value) {
        if (first != null) {
            while (first.getValue() == value) {
                first = first.getNextNode();
                UpdateIndexes(first);
                length--;
            }
            NodeListElement current = first;
            while(current.getNextNode()!=null){
                if(current.getNextNode().getValue()==value){                    ;
                    removeNextNode(current);
                }
                else{
                    current = current.getNextNode();
                }
            }
        } else
            System.out.println("Массив пуст");
    }

    public void removeByIndex(int index) {
        if (first != null && index < length) {
            if (index == 0) {
                first = first.getNextNode();
                UpdateIndexes(first);
                length--;
            } else {
                NodeListElement current = first;
                for(int i=0;i<index-1;i++)
                    current = current.getNextNode();
                removeNextNode(current);
            }
        } else
            System.out.println((first == null) ? "Массив пуст" : "Индекс превышает длинну массива, Length=" + length);
    }

    public Node get(int index) {
        if (index < length) {
            NodeListElement current = first;
            while (current.getIndex() != index) {
                current = current.getNextNode();
            }
            return new Node().CopyOf(current);
        } else {
            System.out.println("Индекс превышает длинну массива, Length=" + length);
            return null;
        }
    }

    public void set(int index, Node node) {
        if (first != null && index < length) {
            NodeListElement nodeListElement = new NodeListElement(node,index);
            if (index == 0) {
                nodeListElement.setNextNode(first.getNextNode());
                first = nodeListElement;
            } else {
                NodeListElement current = first;
                for(int i=0;i<index-1;i++)
                    current = current.getNextNode();
                nodeListElement.setNextNode(current.getNextNode().getNextNode());
                current.setNextNode(nodeListElement);
                if(nodeListElement.getNextNode()==null)
                    last = nodeListElement;
            }
        } else
            System.out.println((first == null) ? "Массив пуст" : "Индекс превышает длинну массива, Length=" + length);
    }

    public int size() {
        return length;
    }

    public NodeList find(int value) {
        if (first != null) {
            NodeList nodeList = new NodeList();
            NodeListElement current = first;
            while (current != null) {
                if (current.getValue() == value)
                    nodeList.add(current);
                current = current.getNextNode();
            }
            return nodeList;
        } else {
            System.out.println("Массив пуст");
            return null;
        }
    }

    public Node first() {
        if (first != null)
            return new Node().CopyOf(first);
        else {
            System.out.println("Массив пуст");
            return null;
        }
    }

    public boolean contains(int value) {
        NodeListElement current = first;
        while (current != null) {
            if (current.getValue() == value)
                return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private void removeNextNode(NodeListElement node){
        node.setNextNode(node.getNextNode().getNextNode());
        if (node.getNextNode() != null)
            UpdateIndexes(node.getNextNode());
        else
            last = node;
        length--;
    }

    private void UpdateIndexes(NodeListElement node) {
        node.setIndex(node.getIndex() - 1);
        if (node.getNextNode() != null)
            UpdateIndexes(node.getNextNode());
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        NodeListElement next = first;
        while (next != null) {
            string.append("Index: ").append(next.getIndex()).append("\t").append("Value: ").append(next.getValue()).append("\n");
            next = next.getNextNode();
        }
        string.append("Length: " + length).append("\n");
        return string.toString();
    }
}
