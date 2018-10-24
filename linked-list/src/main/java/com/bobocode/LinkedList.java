package com.bobocode;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private int size;

    private static final class Node<T> {
        T element;
        Node<T> next;

        Node(T element) {
            this.element = element;
        }

        static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        List<T> list = new LinkedList<>();
        for(T element : elements) {
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
        Node<T> newNode = Node.valueOf(element);
        if (head == null) {
            head = newNode;
        }
        else {
            Node<T> iterationNode = head;
            while(iterationNode.next != null) {
                iterationNode = iterationNode.next;
            }
            iterationNode.next = newNode;
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index can't be negative or bigger than size");
        }
        Node<T> newNode = Node.valueOf(element);
        Node<T> previousIndex = findByIndex(index-1);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        }
        else {
            newNode.next = findByIndex(index);
            previousIndex.next = newNode;
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
        checkBoundsCondition(index);
        Node<T> newNode = Node.valueOf(element);
        if (index == 0) {
            head.element = newNode.element;
        }
        else {
            Node<T> indexNode = findByIndex(index);
            indexNode.element = newNode.element;
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
    public T get(int index) {
        checkBoundsCondition(index);
        Node<T> indexNode = findByIndex(index);
        return indexNode.element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     */
    @Override
    public void remove(int index) {
        checkBoundsCondition(index);
        if (index == 0) {
            head = head.next;
        }
        else {
            Node<T> previousToRemoveNode = findByIndex(index-1);
            previousToRemoveNode.next = previousToRemoveNode.next.next;
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
        Node<T> iterationNode = head;
        while (iterationNode != null) {
            if (iterationNode.element.equals(element)) {
                return true;
            }
            iterationNode = iterationNode.next;
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
        head = null;
        size = 0;
    }

    private Node<T> findByIndex(int index) {
        Node<T> findedNode = head;
        int count = 0;
        while(count < index) {
            findedNode = findedNode.next;
            count++;
        }
        return findedNode;
    }

    private void checkBoundsCondition(int index) {
        if(isEmpty() || index >= size || index < 0) {
            throw new IndexOutOfBoundsException("LinkedList is empty or illegal index");
        }
    }
}
