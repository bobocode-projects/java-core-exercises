package com.bobocode;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
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

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        List<T> list = new LinkedList<>();
        Stream.of(elements).forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
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

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        checkBoundsToAddAt(index);
        Node<T> newNode = Node.valueOf(element);
        if (index == 0) {
            newNode.next = head;
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

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        verifyElementExistAt(index);
        Node<T> node = findNodeByIndex(index);
        node.element = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
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

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
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

    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
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

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}