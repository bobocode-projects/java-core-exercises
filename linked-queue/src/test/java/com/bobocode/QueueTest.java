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

        assertEquals(5, integerQueue.poll().intValue());
    }

    @Test
    public void testPollElement() {
        integerQueue.add(33);
        integerQueue.add(123);
        integerQueue.add(222);

        integerQueue.poll();

        assertEquals(123, integerQueue.poll().intValue());
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

}
