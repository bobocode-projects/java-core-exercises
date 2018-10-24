package com.bobocode;

/**
 * This queue should be implemented using generic liked nodes. E.g. class Node<T>. In addition, this specific
 * should be thread-safe, which means that queue can be used by different threads simultaneously, and should work correct.
 *
 * @param <T> a generic parameter
 */
public class ConcurrentLinkedQueue<T> implements Queue<T> {
    @Override
    public void add(T element) {
        throw new UnsupportedOperationException("This method is not implemented yet"); // todo: implement this method
    }

    @Override
    public T poll() {
        throw new UnsupportedOperationException("This method is not implemented yet"); // todo: implement this method
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("This method is not implemented yet"); // todo: implement this method
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("This method is not implemented yet"); // todo: implement this method
    }
}
