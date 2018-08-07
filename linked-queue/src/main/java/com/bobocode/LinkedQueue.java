package com.bobocode;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>
 *
 * @param <T> a generic parameter
 */
public class LinkedQueue<T> implements Queue<T> {
    static final class Node<T> {
        private T element;
        private Node<T> next;

        static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }

        private Node(T element) {
            this.element = element;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T element) {
        Node<T> newNode = Node.valueOf(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T poll() {
        if (head != null) {
            T element = head.element;
            head = head.next;
            if (head == null) {
                tail = null;
            }
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
