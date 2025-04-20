package linklist;

import java.util.function.Consumer;
import java.util.function.Function;

class Node<T> {
    private Node<T> next;
    private Node<T> prev;
    private T value;

    public Node() {
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

public class DoubleLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    public DoubleLinkedList() {
    }

    private void addToEmptyLinkedList(T value) {
        this.head = new Node<>();
        this.head.setValue(value);
        this.tail = this.head;
    }

    public void add(T value) {
        if (this.head == null) {
            this.addToEmptyLinkedList(value);
        } else {
            Node<T> element = new Node<>();
            element.setValue(value);
            element.setNext(this.head);

            this.head.setPrev(element);
            this.head = element;
        }
    }

    void delete(T value) {
        if (!(this.head == null)) {
            Node<T> currentNode = this.head;

            while (currentNode != null) {
                if (currentNode.getValue().equals(value)) {

                    Node<T> prev = currentNode.getPrev();
                    Node<T> next = currentNode.getNext();
                    prev.setNext(next);
                    if (next != null) {
                        next.setPrev(prev);
                    }
                    break;
                } else {
                    currentNode = currentNode.getNext();
                }
            }
        }
    }

    public <K> DoubleLinkedList<K> map(Function<T, K> function) {
        Node<T> currentNode = this.head;
        DoubleLinkedList<K> newDoubleLinkedList = new DoubleLinkedList<>();
        if (this.head == null) {
            return newDoubleLinkedList;
        }
        while (currentNode != null) {
            newDoubleLinkedList.add(function.apply(currentNode.getValue()));
            currentNode = currentNode.getNext();
        }

        return newDoubleLinkedList;
    }

    void forEach(Consumer<T> consumer) {
        Node<T> currentNode = this.head;
        if (this.head != null) {
            while (currentNode != null) {
                consumer.accept(currentNode.getValue());
                currentNode = currentNode.getNext();
            }
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.delete(2);
        list.forEach(System.out::println);
    }

}
