package com.bobocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class LinkedListTest {

    private List<Integer> intList = new LinkedList<>();

    @Test
    public void testAddIntoEmptyList() {
        intList.add(41);

        assertEquals(1, intList.size());
    }

    @Test
    public void testGetFirstElementFromSingleElementList() {
        intList.add(25);

        int element = intList.get(0);

        assertEquals(25, element);
    }

    @Test
    public void testAddElements() {
        intList = LinkedList.of(43, 233, 54);

        assertEquals(3, intList.size());
    }


    @Test
    public void testGetElements() {
        intList = LinkedList.of(25, 87, 45);

        int firstElement = intList.get(0);
        int secondElement = intList.get(1);
        int thirdElement = intList.get(2);

        assertEquals(25, firstElement);
        assertEquals(87, secondElement);
        assertEquals(45, thirdElement);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetFirstElementFromEmptyList() {
        intList.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetElementByNegativeIndex() {
        intList.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetElementByIndexEqualsToListSize() {
        intList = LinkedList.of(33, 46, 25, 87, 45);

        intList.get(5);
    }

    @Test
    public void testAddElementByZeroIndexIntoEmptyList() {
        intList.add(0, 45);

        assertEquals(1, intList.size());
    }

    @Test
    public void testAddElementByIndexToTheEndOfList() {
        intList = LinkedList.of(98, 64, 23, 1, 3, 4);

        int newElementIndex = intList.size();
        intList.add(newElementIndex, 44);

        assertEquals(44, intList.get(newElementIndex).intValue());
    }

    @Test
    public void testAddElementToTheHeadOfNonEmptyList() {
        intList = LinkedList.of(4, 6, 8, 9, 0, 2);

        intList.add(0, 53);

        assertEquals(53, intList.get(0).intValue());
        assertEquals(4, intList.get(1).intValue());
        assertEquals(7, intList.size());
    }

    @Test
    public void testAddElementByIndex() {
        intList = LinkedList.of(43, 5, 6, 8);

        int newElementIdx = 2;
        intList.add(newElementIdx, 66);

        assertEquals(66, intList.get(newElementIdx).intValue());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddElementByNegativeIndex() {
        intList.add(-1, 66);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddElementByIndexLargerThanListSize() {
        intList = LinkedList.of(4, 6, 11, 9);

        int newElementIdx = 5;
        intList.add(newElementIdx, 88);
    }

    @Test
    public void testSetFirstElementOnEmptyTree() {
        intList.set(0, 34);

        assertEquals(34, intList.get(0).intValue());
    }

    @Test
    public void testSetElement() {
        intList = LinkedList.of(34, 78, 9, 8);

        int index = 2; //element = 78
        intList.set(index, 99);

        assertEquals(99, intList.get(index).intValue());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveElementFromEmptyList() {
        intList.remove(234);
    }

    @Test
    public void testRemoveFirstElement() {
        intList = LinkedList.of(4, 6, 8, 9);

        intList.remove(0);

        assertEquals(6, intList.get(0).intValue());
    }

    @Test
    public void testRemoveLastElement() {
        intList = LinkedList.of(4, 6, 8, 9);

        intList.remove(intList.size() - 1);

        assertEquals(8, intList.get(intList.size() - 1).intValue());
    }

    @Test
    public void testRemoveElement() {
        intList = LinkedList.of(1, 2, 3, 4, 5);

        int elementIndex = 2;
        intList.remove(elementIndex); // element = 3

        assertEquals(4, intList.get(elementIndex).intValue());
    }

    @Test
    public void testContainsOnEmptyList() {
        boolean contains = intList.contains(34);

        assertEquals(false, contains);
    }

    @Test
    public void testContains() {
        intList = LinkedList.of(45, 6, 3, 6);

        boolean containsExistingElement = intList.contains(3);
        boolean containsNotExistingElement = intList.contains(54);

        assertEquals(true, containsExistingElement);
        assertEquals(false, containsNotExistingElement);
    }

    @Test
    public void testIsEmptyOnEmptyList() {
        boolean empty = intList.isEmpty();

        assertEquals(true, empty);
    }

    @Test
    public void testIsEmpty() {
        intList = LinkedList.of(34, 5, 6);

        boolean empty = intList.isEmpty();

        assertEquals(false, empty);
    }

    @Test
    public void testSizeOnEmptyList() {
        int size = intList.size();

        assertEquals(0, size);
    }

    @Test
    public void testSize() {
        intList = LinkedList.of(4, 7, 9, 0, 7);

        int size = intList.size();

        assertEquals(5, size);
    }

    @Test
    public void testClearOnEmptyList() {
        intList.clear();

        assertEquals(0, intList.size());
    }

    @Test
    public void testClearChangesTheSize() {
        intList = LinkedList.of(4, 5, 6);

        intList.clear();

        assertEquals(0, intList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testClearRemovesElements() {
        intList = LinkedList.of(4, 5, 6);

        intList.clear();
        intList.get(0);
    }


}
