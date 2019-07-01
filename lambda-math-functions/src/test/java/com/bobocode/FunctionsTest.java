package com.bobocode;

import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class FunctionsTest {
    private FunctionMap<Integer, Integer> integerFunctionMap;

    @Before
    public void init(){
        integerFunctionMap = Functions.intFunctionMap();
    }

    @Test
    public void testSquareFunction() {
        Function<Integer, Integer> squareFunction = integerFunctionMap.getFunction("square");

        int actualResult = squareFunction.apply(5);

        assertEquals(25, actualResult);
    }

    @Test
    public void testAbsFunction(){
        Function<Integer, Integer> absFunction = integerFunctionMap.getFunction("abs");

        int actualResult = absFunction.apply(-192);

        assertEquals(192, actualResult);
    }

    @Test
    public void testIncrementFunction(){
        Function<Integer, Integer> incrementFunction = integerFunctionMap.getFunction("increment");

        int actualResult = incrementFunction.apply(399);

        assertEquals(400, actualResult);
    }

    @Test
    public void testDecrementFunction(){
        Function<Integer, Integer> decrementFunction = integerFunctionMap.getFunction("decrement");

        int actualResult = decrementFunction.apply(800);

        assertEquals(799, actualResult);
    }

    @Test
    public void testSignFunctionOnNegativeValue(){
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int actualResult = signFunction.apply(-123);

        assertEquals(-1, actualResult);
    }

    @Test
    public void testSignFunctionOnPositiveValue(){
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int actualResult = signFunction.apply(23);

        assertEquals(1, actualResult);
    }

    @Test
    public void testSignFunctionOnZero(){
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int actualResult = signFunction.apply(0);

        assertEquals(0, actualResult);
    }

    @Test(expected = InvalidFunctionNameException.class)
    public void testGetUnknownFunction(){
        integerFunctionMap.getFunction("sqrt");
    }
}
