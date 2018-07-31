package com.bobocode;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>
 *
 * @param <T> a generic parameter
 */
public class LinkedQueue<T> implements Queue<T> {
    private Node<T> head;
    private int size;

    @Override
    public void add(T element) {
        Node<T> newNode = Node.valueOf(element);
        if (head != null) {
            newNode.setNext(head);
        }
        head = newNode;
        size++;
    }

    @Override
    public T poll() {
        if (head != null) {
            T element = head.getElement();
            head = head.getNext();
            size--;
            return element;
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
