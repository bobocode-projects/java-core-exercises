package com.bobocode;

/**
 * This queue should be implemented using generic linked nodes. E.g. class Node<T>
 *
 * @param <T> a generic parameter
 */
public class LinkedQueue<T> implements Queue<T> {
    Node<T> head;
    Node<T> tail;
    int size = 0;

    @Override
    public void add(T element) {
        if (size == 0) addFirstElementInQueue(element);
        else {
            tail.next = new Node<T>(element, null);
            tail = tail.next;
            size++;
        }
    }

    private void addFirstElementInQueue(T element) {
        head = new Node<>(element, null);
        tail = head;
        size++;
    }

    @Override
    public T poll() {
        T element;
        if (size == 0) return null;
        if (head == tail) element = removeLastElementInQueue();
        else {
            element = head.getElement();
            head = head.next;
            size--;
        }
        return element;
    }

    private T removeLastElementInQueue() {
        T element = head.getElement();
        tail = null;
        head = null;
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    private class Node<T> {
        T element;
        Node<T> next;

        public T getElement() {
            return element;
        }

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }
}
