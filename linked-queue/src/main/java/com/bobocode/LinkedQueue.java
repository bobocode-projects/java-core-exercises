package com.bobocode;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>
 *
 * @param <T> a generic parameter
 */
public class LinkedQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T element) {
        Node<T> newNode = Node.valueOf(element);
        if(head == null){
            head = tail = newNode;
        }else {
            tail.setNextNode(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public T poll() {
        if(head != null){
            T element = head.getElement();
            head = head.getNextNode();
            size--;
            return element;
        } else
            return null;
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
