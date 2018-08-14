package com.bobocode;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A n is implemented as
 * inner static class {@link Node<T>}
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {
    static class Node<T> {
        T e;
        Node<T> n;

        public Node(T e) {
            this.e = e;
        }
    }

    private Node<T> h;
    private int s;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        LinkedList<T> l = new LinkedList<>();
        for (T e : elements) {
            l.add(e);
        }
        return l;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (h == null) {
            h = new Node<>(element);
        } else {
            Node<T> n = new Node<>(element);
            Node<T> i = h;
            while (i.n != null) {
                i = i.n;
            }
            i.n = n;
        }
        s++;
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
        if (index < 0 || index > s) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index == 0) {
                Node<T> nn = new Node<>(element);
                nn.n = h;
                h = nn;
                s++;
            } else {
                Node<T> cn = h;
                for (int i = 0; i < index - 1; i++) {
                    cn = cn.n;
                }
                Node<T> nn = new Node<>(element);
                nn.n = cn.n;
                cn.n = nn;
                s++;
            }
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
        if (index < 0 || index >= s) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index == 0) {
                h.e = element;
            } else {
                Node<T> cn = h;
                for (int i = 0; i < index - 1; i++) {
                    cn = cn.n;
                }
                Node<T> nn = new Node<>(element);
                cn.n = nn;
                nn.n = cn.n.n;
            }
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
        if (index < 0 || index >= s) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<T> cn = h;
            for (int i = 0; i < index; i++) {
                cn = cn.n;
            }
            return cn.e;
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
        if (index < 0 || index >= s) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index == 0) {
                h = h.n;
                s--;
            } else {
                Node<T> cn = h;
                for (int i = 0; i < index - 1; i++) {
                    cn = cn.n;
                }
                cn.n = cn.n.n;
                s--;
            }
        }
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> cn = h;
        boolean r = false;
        while (cn != null) {
            if (cn.e.equals(element)) {
                r = true;
            }
            cn = cn.n;
        }
        return r;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        boolean r = false;
        if (s == 0) {
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
        return s;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        h = null;
        s = 0;
    }
}
