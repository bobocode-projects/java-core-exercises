package com.bobocode;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private int size = 0;

    /**
     * Constructs an empty list
     */
    public LinkedList() {
        head = null;
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        LinkedList list = new LinkedList();

        for (T type : elements) {
            list.add(type);
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
        if (head == null) head = new Node<>(element, null);
        else {
            Node<T> tmp = head;
            while (tmp.next != null) tmp = tmp.next;

            tmp.next = new Node<>(element, null);
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
    public void add(int index, T element) {
        Node<T> tmp = head;
        if (index > size || index < 0) throw new IndexOutOfBoundsException();
        if (index == 0) head = new Node<>(element, head);
        else {
            while (tmp != null && index > 1) {
                tmp = tmp.next;
                index--;
            }
            tmp.next = new Node<>(element, tmp.next);
        }
        size++;

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
        Node<T> tmp = head;
        if (index > size - 1 || head == null) throw new IndexOutOfBoundsException();
        if (index == 0) head.data = element;
        else {
            while (tmp != null && index > 0) {
                tmp = tmp.next;
                index--;
            }
            tmp.data = element;
        }
        size--;
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
        if (index >= size || index < 0 || size == 0) throw new IndexOutOfBoundsException();
        Node<T> tmp = head;
        while (tmp != null && index > 0) {
            tmp = tmp.next;
            index--;
        }
        return tmp.data;

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
        Node<T> tmp = head;
        if (index > size - 1 || head == null) throw new IndexOutOfBoundsException();
        if (index == 0) head = head.next;
        else {
            while (tmp != null && index > 1) {
                tmp = tmp.next;
                index--;
            }
            tmp.next = tmp.next.next;
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
        Node<T> tmp = head;
        while (tmp != null) {
            if (tmp.data.equals(element)) return true;
            tmp = tmp.next;
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
        boolean isEmpty = (size == 0) ? true : false;
        return isEmpty;
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
        if (size == 0) head = null;
        while (head != null) {
            remove(0);
        }
    }

    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

}
