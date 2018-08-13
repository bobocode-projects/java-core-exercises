package com.bobocode;

import java.util.Objects;
import java.util.stream.Stream;

public class LinkedList<T> implements List<T> {
    final static class Node<T> {
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
    private int size;

    public static <T> List<T> of(T... elements) {
        List<T> list = new LinkedList<>();
        Stream.of(elements).forEach(list::add);
        return list;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = Node.valueOf(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> tail = findTail(head);
            tail.next = newNode;
        }
        size++;
    }

    /**
     * In order to make the implementation simpler this method is used instead of having one more additional parameter
     * tail
     *
     * @param head the first element in the list
     * @return the last element in the list
     */
    private Node<T> findTail(Node<T> head) {
        Node<T> currentNode = Objects.requireNonNull(head);
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    public void add(int index, T element) {
        checkBoundsToAddAt(index);
        Node<T> newNode = Node.valueOf(element);
        if (index == 0) {
            if (head != null) {
                newNode.next = head;
            }
            head = newNode;
        } else {
            Node<T> node = findNodeByIndex(index - 1);
            newNode.next = node.next;
            node.next = newNode;
        }
        size++;
    }

    private void checkBoundsToAddAt(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void set(int index, T element) {
        verifyElementExistAt(index);
        if (index == 0) {
            if (head == null) {
                head = Node.valueOf(element);
                size++;
            } else {
                head.element = element;
            }
        } else {
            Node<T> node = findNodeByIndex(index);
            node.element = element;
        }
    }

    @Override
    public T get(int index) {
        Node<T> node = findNodeByIndex(index);
        return node.element;
    }

    private Node<T> findNodeByIndex(int index) {
        verifyElementExistAt(index);
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    private void verifyElementExistAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void remove(int index) {
        verifyElementExistAt(index);
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> previousNode = findNodeByIndex(index - 1);
            previousNode.next = previousNode.next.next;
        }
        size--;
    }


    @Override
    public boolean contains(T element) {
        if (head == null) {
            return false;
        } else {
            return exists(element);
        }
    }

    private boolean exists(T element) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.element.equals(element)) {
                return true;
            }
            currentNode = currentNode.next;
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}