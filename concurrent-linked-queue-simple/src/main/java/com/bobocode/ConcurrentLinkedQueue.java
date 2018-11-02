package com.bobocode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>. In addition, this specific
 * should be thread-safe, which means that queue can be used by different threads simultaneously, and should work correct.
 *
 * @param <T> a generic parameter
 */
public class ConcurrentLinkedQueue<T> implements Queue<T> {
    static class Node<T> {
        T element;
        Node<T> next;

        private Node(T element) {
            this.element = element;
        }

        static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private AtomicInteger size;

    @Override
    public synchronized void add(T element) {
        Node<T> newNode = Node.valueOf(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size.incrementAndGet();
    }

    @Override
    public synchronized T poll() {
        if (head == null){
            return null;
        }else {
            Node<T> headNode = head;
            head = head.next;
            if(head==null){
                tail = null;
            }
            size.decrementAndGet();
            return headNode.element;
        }
    }

    @Override
    public int size() {
        return size.intValue();
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }
}
