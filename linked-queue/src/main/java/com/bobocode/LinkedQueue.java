package com.bobocode;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>
 *
 * @param <T> a generic parameter
 */
public class LinkedQueue<T> implements Queue<T> {

    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> first;
    private Node<T> last;

    @Override
    public void add(T element) {
        Node<T>newNode = new Node<>(element);
        if (first == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = last.next;
        }
        size++;
    }

    @Override
    public T poll() {
        if (!isEmpty()) {
            T result = first.data;
            first = first.next;
            return result;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }
}