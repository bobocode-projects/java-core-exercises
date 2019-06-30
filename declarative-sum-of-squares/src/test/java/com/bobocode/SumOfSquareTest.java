package com.bobocode;

import com.bobocode.exception.InvalidRangeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SumOfSquareTest {

    @Test
    public void testCalculateSumOfSquaresOfZero() {
        int sumOfSquares = SumOfSquares.calculateSumOfSquaresInRange(0, 0);

        assertEquals(0, sumOfSquares);
    }

    @Test
    public void testCalculateSumOfSquaresOfOne() {
        int sumOfSquares = SumOfSquares.calculateSumOfSquaresInRange(0, 1);

        assertEquals(1, sumOfSquares);
    }

    @Test
    public void testCalculateSumOfSquares() {
        int sumOfSquares = SumOfSquares.calculateSumOfSquaresInRange(1, 5); // 1*1 + 2*2 + 3*3 + 4*4 + 5*5 = 55

        assertEquals(55, sumOfSquares);
    }

    @Test
    public void testCalculateSumOfSquaresOnNegative() {
        int sumOfSquares = SumOfSquares.calculateSumOfSquaresInRange(-4, -2); // -4*(-4) + -3*(-3) + -2*(-2) = 29

        assertEquals(29, sumOfSquares);
    }

    @Test(expected = InvalidRangeException.class)
    public void testWithInvalidRange() {
        SumOfSquares.calculateSumOfSquaresInRange(4, 1);
    }

}
