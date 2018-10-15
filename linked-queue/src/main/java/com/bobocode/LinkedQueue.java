package com.bobocode;

import java.util.NoSuchElementException;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>
 *
 * @param <T> a generic parameter
 */
public class LinkedQueue<T> implements Queue<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;


    private static class Node<T> {
        private T currentObject;
        private Node<T> nextObject;

         T getCurrentObject() {
            return currentObject;
        }

         void setCurrentObject(T currentObject) {
            this.currentObject = currentObject;
        }

         Node<T> getNextObject() {
            return nextObject;
        }

         void setNextObject(Node<T> nextObject) {
            this.nextObject = nextObject;
        }
    }

    @Override
    public void add(T element) {

        size++;
        Node<T> newNode = new Node<>();

        newNode.setCurrentObject(element);
        if (isEmpty()) {
            head = tail = newNode;
        }
        else {
            Node<T> oldNode = tail;
            tail = newNode;
            oldNode.setNextObject(tail);
        }
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        } else{
            Node<T> oldHead = head;
            if (size == 1){
                tail = head = null;
            }
            else{
                head = oldHead.getNextObject();
            }


        return oldHead.getCurrentObject();
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
