package homework_3_26_10.task2_26_10;

import java.util.ArrayList;
import java.util.List;

public class NodeList {
    private NodeListElement first;
    private int length = 0;

    public void add(Node node) {
        if (first != null) {
            NodeListElement next = first;
            while (next.getNextNode() != null) {
                next = next.getNextNode();
            }
            next.setNextNode(new NodeListElement(node));
            next.getNextNode().setIndex(next.getIndex() + 1);
            length++;
        } else {
            first = new NodeListElement(node);
            first.setIndex(0);
            length++;
        }
        List<Integer> inn = new ArrayList<>();
    }

    public void removeByValue(int value) {
        if (first != null) {
            while (first.getValue() == value) {
                first = first.getNextNode();
                UpdateIndexes(first);
                length--;
            }
            NodeListElement current = first.getNextNode();
            NodeListElement previous = first;
            remove(previous, current, value);
        } else
            System.out.println("Массив пуст");
    }

    public void removeByIndex(int index) {
        if (first != null && index < length) {
            if (index == 0) {
                first = first.getNextNode();
                UpdateIndexes(first);
            } else {
                NodeListElement current = first.getNextNode();
                NodeListElement previous = first;
                while (current.getIndex() != index) {
                    previous = current;
                    current = current.getNextNode();
                }
                previous.setNextNode(current.getNextNode());
                if (previous.getNextNode() != null)
                    UpdateIndexes(previous.getNextNode());
            }
            length--;
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
            NodeListElement nodeListElement = new NodeListElement(node);
            if (index == 0) {
                nodeListElement.setNextNode(first.getNextNode());
                nodeListElement.setIndex(first.getIndex());
                first = nodeListElement;
            } else {
                NodeListElement current = first.getNextNode();
                NodeListElement previous = first;
                while (current.getIndex() != index) {
                    previous = current;
                    current = current.getNextNode();
                }
                previous.setNextNode(nodeListElement);
                nodeListElement.setNextNode(current.getNextNode());
                nodeListElement.setIndex(current.getIndex());
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

    private void remove(NodeListElement previousNode, NodeListElement currentNode, int value) {
        if (currentNode.getValue() == value) {
            previousNode.setNextNode(currentNode.getNextNode());
            length--;
            if (previousNode.getNextNode() != null) {
                UpdateIndexes(previousNode.getNextNode());
                remove(previousNode, previousNode.getNextNode(), value);
            }
        } else {
            if (currentNode.getNextNode() != null)
                remove(currentNode, currentNode.getNextNode(), value);
        }
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
