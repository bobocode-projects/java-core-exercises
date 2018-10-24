package com.bobocode;

import java.util.Objects;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {
    private static class Node<T> {
        T element;
        Node<T> nextValue;

        public Node(T element) {
            this.element = element;
        }
    }

    private int size;
    private Node<T> head;
    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        List<T> list = new LinkedList<>();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<T>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> currentNode = head;
            while (currentNode.nextValue != null) {
                currentNode = currentNode.nextValue;
            }
            currentNode.nextValue = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException {
        Node<T> newNode = new Node<>(element);

        if (index == 0) {
            newNode.nextValue = head;
            head = newNode;
            size++;
        } else {
            Node<T> currentNode = findByIndex(index - 1);
            newNode.nextValue = currentNode.nextValue;
            currentNode.nextValue = newNode;
            size++;
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
    public void set(int index, T element) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, size);
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.nextValue = head.nextValue;
            head = newNode;
        } else {
            Node<T> prevNode = findByIndex(index - 1);
            Node<T> currentNode = prevNode.nextValue;
            newNode.nextValue = currentNode.nextValue;
            prevNode.nextValue = newNode;
        }
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        return findByIndex(index).element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     */
    @Override
    public void remove(int index) throws IndexOutOfBoundsException {
        if (index == 0) {
            head = head.nextValue;
        } else {
            Node<T> prevNode = findByIndex(index - 1);
            Node<T> nodeToRemove = prevNode.nextValue;
            prevNode.nextValue = nodeToRemove.nextValue;
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
            Node<T> currentNode = head;
            while (currentNode.nextValue != null) {
                if (currentNode.element.equals(element)) {
                    return true;
                }
                currentNode = currentNode.nextValue;
            }
            return false;
        }
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
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
        size = 0;
        head = null;
    }

    private Node<T> findByIndex(int index) {
        Node<T> node = head;
        int counter = 0;
        while (counter < index) {
            node = node.nextValue;
            counter++;
        }
        return node;
    }


}
