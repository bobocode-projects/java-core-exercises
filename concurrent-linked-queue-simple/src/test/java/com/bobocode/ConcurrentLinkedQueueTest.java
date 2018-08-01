package com.bobocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ConcurrentLinkedQueueTest {

    private Queue<Integer> integerQueue = new ConcurrentLinkedQueue<>();

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
        integerQueue.add(88);
        integerQueue.add(366);
        integerQueue.add(5);

        assertEquals(324, integerQueue.poll().intValue());
    }

    @Test
    public void testPollElement() {
        integerQueue.add(12);
        integerQueue.add(333);
        integerQueue.add(98);
        integerQueue.add(344);

        integerQueue.poll(); // should poll 12
        integerQueue.poll(); // should poll 333

        assertEquals(98, integerQueue.poll().intValue());
    }

    @Test
    public void testAddElementsConcurrent() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<Integer> c = () -> IntStream.rangeClosed(1, ThreadLocalRandom.current().nextInt(1000))
                .peek(integerQueue::add).sum();
        List<Callable<Integer>> callableList = IntStream.rangeClosed(1, 10).mapToObj(i -> c).collect(toList());

        List<Future<Integer>> futureList = executorService.invokeAll(callableList);
        int expectedSum = 0;
        for (Future<Integer> future : futureList) {
            expectedSum += future.get();
        }

        int actualSum = 0;
        while (!integerQueue.isEmpty()) {
            actualSum += integerQueue.poll();
        }

        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void testPollElementsConcurrent() throws InterruptedException, ExecutionException {
        int expectedSum = IntStream.rangeClosed(1, ThreadLocalRandom.current().nextInt(10000))
                .peek(integerQueue::add)
                .sum();


        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<Integer> c = () -> {
            int localSum = 0;
            while (!integerQueue.isEmpty()) {
                localSum += integerQueue.poll();
            }
            return localSum;
        };

        List<Callable<Integer>> callableList = IntStream.rangeClosed(1, 10).mapToObj(i -> c).collect(toList());

        List<Future<Integer>> futureList = executorService.invokeAll(callableList);
        int actualSum = 0;
        for (Future<Integer> future : futureList) {
            actualSum += future.get();
        }


        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void testSizeConcurrent() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<Integer> c = () -> IntStream.rangeClosed(1, 10_000).peek(integerQueue::add).sum();
        List<Callable<Integer>> callableList = IntStream.rangeClosed(1, 10).mapToObj(i -> c).collect(toList());

        executorService.invokeAll(callableList);

        assertEquals(100_000, integerQueue.size());
    }


}
