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
        if (head==null){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T poll() {
        if(head==null){
            return null;
        }else {
            Node<T> headNode = head;
            head = head.next;
            if (head==null){
                tail = null;
            }
            size--;
            return headNode.element;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }
    static class Node<T>{
        T element;
        Node<T> next;

        private Node(T element) {
            this.element = element;
        }

        static <T> Node<T> valueOf(T element){
            return new Node<>(element);
        }
    }
}
