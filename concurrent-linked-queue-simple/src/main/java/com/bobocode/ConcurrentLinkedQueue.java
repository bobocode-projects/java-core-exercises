package com.bobocode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>. In addition, this specific
 * should be thread-safe, which means that queue can be used by different threads simultaneously, and should work correct.
 *
 * @param <T> a generic parameter
 */
public class ConcurrentLinkedQueue<T> implements Queue<T> {
    private Node<T> head;
    private Node<T> tail;
    private AtomicInteger size = new AtomicInteger();

    @Override
    synchronized public void add(T element) {
        Node<T> newNode = Node.valueOf(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size.incrementAndGet();
    }

    @Override
    synchronized public T poll() {
        if (head != null) {
            T element = head.getElement();
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            size.decrementAndGet();
            return element;
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size.get();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
