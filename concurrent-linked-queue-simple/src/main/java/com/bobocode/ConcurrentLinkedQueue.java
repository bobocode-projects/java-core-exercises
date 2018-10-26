package com.bobocode;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>. In addition, this specific
 * should be thread-safe, which means that queue can be used by different threads simultaneously, and should work correct.
 *
 * @param <T> a generic parameter
 */
public class ConcurrentLinkedQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static final class Node<T> {

        T element;
        Node<T> next;


        private Node(T element) {
            this.element = element;
        }

        static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }
    }

    @Override
    public synchronized void add(T element) {
        Node<T> newNode = Node.valueOf(element);
        if (head == null) {
            head = newNode;
        }
        else {
            tail.next = newNode;
        }
            tail = newNode;
        size++;
    }

    @Override
    public synchronized T poll() {
        if(head != null) {
            T element = head.element;
            head = head.next;
            if(head == null) {
                tail = null;
            }
            size--;
            return element;
        }
        else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
