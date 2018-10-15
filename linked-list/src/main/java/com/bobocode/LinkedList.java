package com.bobocode;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {

        private T elementOfNode;
        private Node<T> nextNode;
        private Node<T> previousNode;

        public Node(T elementOfNode) {
            this.elementOfNode = elementOfNode;
            this.nextNode = null;
            this.previousNode = null;
        }

        T getElementOfNode() {
            return elementOfNode;
        }

        void setElementOfNode(T elementOfNode) {
            this.elementOfNode = elementOfNode;
        }

        Node<T> getNextNode() {
            return nextNode;
        }

        void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }

        Node<T> getPreviousNode() {
            return previousNode;
        }

        void setPreviousNode(Node<T> previousNode) {
            this.previousNode = previousNode;
        }
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    //todo DONE
    static <T> List<T> of(T... elements) {

        List<T> list = new LinkedList<>();

        for (int i = 0; i < elements.length; i++) {
            list.add(elements[i]);
        }
        return list;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
    // todo DONE
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            Node<T> oldTail = tail;
            oldTail.setNextNode(newNode);
            tail = newNode;
            newNode.setNextNode(null);
            newNode.setPreviousNode(oldTail);
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
    public void add(int index,T element) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<T> newNode = new Node<>(element);

            if (index == 0) {
                if (isEmpty()) {
                    head = tail = newNode;
                } else {
                    Node<T> oldHead = head;
                    head = newNode;
                    newNode.setNextNode(oldHead);
                    oldHead.setPreviousNode(newNode);
                }
            } else if (index == size) {
                Node<T> oldTail = tail;
                newNode.setPreviousNode(oldTail);
                tail = newNode;
                oldTail.setNextNode(tail);
            } else {

                Node<T> previousNodeByIndex = head;
                for (int i = 1; i < index; i++) {
                    previousNodeByIndex = previousNodeByIndex.getNextNode();
                }
                Node<T> nextNodeByIndex = previousNodeByIndex.getNextNode();
                previousNodeByIndex.setNextNode(newNode);
                newNode.setPreviousNode(previousNodeByIndex);
                newNode.setNextNode(nextNodeByIndex);
                nextNodeByIndex.setPreviousNode(newNode);
            }
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
    public void set(int index,T element) {
        //throw new UnsupportedOperationException("This method is not implemented yet"); // todo: implement this method
        if (index < 0 || index > size - 1 || isEmpty()) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index == 0) {
                head.setElementOfNode(element);
            } else if (index == size - 1) {
                tail.setElementOfNode(element);
            } else {
                Node<T> indexedNode = head.getNextNode();
                int i = 1;
                while (i < index) {
                    indexedNode = indexedNode.getNextNode();
                    i++;
                }
                indexedNode.setElementOfNode(element);
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

        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        } else {
            if (isEmpty()) {
                return null;
            } else {
                if (index == 0) {
                    return head.getElementOfNode();
                } else {
                    if (index == size - 1) {
                        return tail.getElementOfNode();
                    } else {
                        Node<T> nodeByIndex = head.getNextNode();
                        for (int i = 1; i < index; i++) {
                            nodeByIndex = nodeByIndex.getNextNode();
                        }
                        return nodeByIndex.getElementOfNode();
                    }
                }
            }
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
        //throw new UnsupportedOperationException("This method is not implemented yet"); // todo: implement this method
        if (index < 0 || index > size - 1 || isEmpty()) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index == 0) {
                head = head.getNextNode();
                head.setPreviousNode(null);
            } else {
                if (index == size - 1) {
                    tail = tail.getPreviousNode();
                    tail.setNextNode(null);
                } else {
                    Node<T> indexedNode = head;
                    int i = 1;
                    while (i < index) {
                        indexedNode = indexedNode.getNextNode();
                        i++;
                    }
                    Node<T> prevNode = indexedNode.getPreviousNode();
                    Node<T> nextNode = indexedNode.getNextNode();
                    prevNode.setNextNode(nextNode);
                    nextNode.setPreviousNode(prevNode);
                }
            }
            size--;
        }
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        //throw new UnsupportedOperationException("This method is not implemented yet"); // todo: implement this method
        Node<T> node = head;
        while (node != null) {
            if (node.getElementOfNode().equals(element)) {
                return true;
            }
            node = node.getNextNode();
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    // todo DONE
    @Override
    public boolean isEmpty() {

        return head == null;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    // todo DONE
    @Override
    public int size() {

        return size;
    }

    /**
     * Removes all list elements
     */
    //todo Done
    @Override
    public void clear() {

        size = 0;
        head = null;
        tail = null;
    }
}
