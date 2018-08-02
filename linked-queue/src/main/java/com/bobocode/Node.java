package com.bobocode;

public class Node<T> {

    private T element;
    private Node<T> nextNode;

    public static <T> Node<T> valueOf(T element){
        return new Node<>(element);
    }

    private Node(T element) {
        this.element = element;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public T getElement() {
        return element;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }
}
