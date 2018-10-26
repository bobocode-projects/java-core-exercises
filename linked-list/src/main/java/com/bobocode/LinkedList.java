package com.bobocode;

import java.util.Arrays;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link ListE <T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {
    static class ListE<T> {
        T val;
        ListE<T> node;
    }

    private ListE<T> start;
    private int counter;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        java.util.List<T> list = Arrays.asList(elements);
        LinkedList<T> list2 = new LinkedList<>();
        for (T e : list) {
            list2.add(e);
        }
        return list2;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param t element to add
     */
    @Override
    public void add(T t) {
        if (start == null) {
            start = new ListE<>();
            start.val = t;
            counter++;
        } else {
            ListE<T> n = new ListE<>();
            n.val = t;
            ListE<T> i = start;
            while (i.node != null) { // goes until it finds the list tail
                i = i.node;
            }
            i.node = n;
            counter++;
        }
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param i an index of new element
     * @param t element to add
     */
    @Override
    public void add(int i, T t) {
        if (i < 0 || i > counter) {
            throw new IndexOutOfBoundsException();
        } else {
            if (i == 0) {
                ListE<T> nn = new ListE<>();
                nn.val = t;
                nn.node = start;
                start = nn;
                counter++;
            } else {
                ListE<T> cn = start;
                for (int j = 0; j < i - 1; j++) { // looking for previous node to be able to attach new one to it
                    cn = cn.node;
                }
                ListE<T> nn = new ListE<>();
                nn.val = t;
                nn.node = cn.node;
                cn.node = nn;
                counter++;
            }
        }
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param i an position of element to change
     * @param t a new element value
     */
    @Override
    public void set(int i, T t) {
        if (i < 0 || i >= counter) { // checks the index bounds
            throw new IndexOutOfBoundsException();
        } else {
            if (i == 0) { // if index is zero we need to reassign the head reference
                ListE<T> newListE = new ListE<>();
                newListE.val = t;
                newListE.node = start.node;
                start = newListE;
            } else {
                ListE<T> prev = start;
                for (int j = 0; j < i - 1; j++) {
                    prev = prev.node;
                }
                ListE<T> nn = new ListE<>();
                nn.val = t;
                nn.node = prev.node.node;
                prev.node = nn;
            }
        }
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param i element index
     * @return an element value
     */
    @Override
    public T get(int i) {
        if (i < 0 || i >= counter) {// checks the index bounds
            throw new IndexOutOfBoundsException();
        } else {
            ListE<T> cn = start;
            int j = 0;
            while (j < i) {
                cn = cn.node;
                j++;
            }
            return cn.val;
        }
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param i element index
     */
    @Override
    public void remove(int i) {
        if (i < 0 || i >= counter) {
            throw new IndexOutOfBoundsException();
        } else {
            if (i == 0) {
                start = start.node;
                counter--;
            } else {
                ListE<T> cn = start;
                int j = 0;
                while (j < i - 1) {
                    cn = cn.node;
                    j++;
                }
                cn.node = cn.node.node;
                counter--;
            }
        }
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T t) {
        ListE<T> listElemt = start;
        boolean bool = false;
        for (; listElemt != null; ) {
            if (listElemt.val.equals(t)) {
                bool = true;
            }
            listElemt = listElemt.node;
        }
        return bool;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        boolean r = false;
        if (counter == 0) {
            r = true;
        }
        return r;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return counter;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        for (int i = counter - 1; i >= 0; i--) { // removes all elements starting from the end
            remove(i);
        }
    }
}
