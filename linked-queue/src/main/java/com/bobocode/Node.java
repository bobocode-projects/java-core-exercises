package com.bobocode;

/**
 * This class represents a node of a queue.
 *
 * @param <T>
 */
public class Node<T> {
    private T element;
    private Node<T> next;

    public static <T> Node<T> valueOf(T element) {
        return new Node<>(element);
    }

    private Node(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
