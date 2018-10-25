package com.bobocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class QueueTest {

    private Queue<Integer> integerQueue = new LinkedQueue<>();

    @Test
    public void testAddElementIntoEmptyQueue() {
        integerQueue.add(1312);

        assertEquals(1, integerQueue.size());
        assertEquals(1312, integerQueue.poll().intValue());
    }

    @Test
    public void testPollElementFromEmptyQueue() {
        assertNull(integerQueue.poll());
    }

    @Test
    public void testSizeOfEmptyQueue() {
        assertEquals(0, integerQueue.size());
    }

    @Test
    public void testIsEmptyOnEmptyQueue() {
        assertTrue(integerQueue.isEmpty());
    }

    @Test
    public void testAddElement() {
        integerQueue.add(324);
        integerQueue.add(23);
        integerQueue.add(5);

        assertEquals(3, integerQueue.size());
        assertEquals(324, integerQueue.poll().intValue());
    }

    @Test
    public void testPollElement() {
        integerQueue.add(33);
        integerQueue.add(123);
        integerQueue.add(222);
        integerQueue.add(444);

        integerQueue.poll(); // should poll 33

        assertEquals(123, integerQueue.poll().intValue());
        assertEquals(2, integerQueue.size());
    }

    @Test
    public void testSize() {
        integerQueue.add(98);
        integerQueue.add(9);
        integerQueue.add(5);
        integerQueue.add(6);

        assertEquals(4, integerQueue.size());
    }

    @Test
    public void testIsEmpty() {
        integerQueue.add(3);
        integerQueue.add(9);

        assertFalse(integerQueue.isEmpty());

    }

    @Test
    public void testPollLastElement() {
        integerQueue.add(8);
        integerQueue.add(123);
        integerQueue.add(99);
        integerQueue.add(46);

        integerQueue.poll(); // should poll 8
        integerQueue.poll(); // should poll 123
        integerQueue.poll(); // should poll 99

        assertEquals(46, integerQueue.poll().intValue());
        assertEquals(0, integerQueue.size());
    }

}
