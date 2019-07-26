package com.bobocode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FunctionsTest {
    private FunctionMap<Integer, Integer> integerFunctionMap;

    @BeforeEach
    public void init() {
        integerFunctionMap = Functions.intFunctionMap();
    }

    @Test
    void testSquareFunction() {
        Function<Integer, Integer> squareFunction = integerFunctionMap.getFunction("square");

        int actualResult = squareFunction.apply(5);

        assertEquals(25, actualResult);
    }

    @Test
    void testAbsFunction() {
        Function<Integer, Integer> absFunction = integerFunctionMap.getFunction("abs");

        int actualResult = absFunction.apply(-192);

        assertEquals(192, actualResult);
    }

    @Test
    void testIncrementFunction() {
        Function<Integer, Integer> incrementFunction = integerFunctionMap.getFunction("increment");

        int actualResult = incrementFunction.apply(399);

        assertEquals(400, actualResult);
    }

    @Test
    void testDecrementFunction() {
        Function<Integer, Integer> decrementFunction = integerFunctionMap.getFunction("decrement");

        int actualResult = decrementFunction.apply(800);

        assertEquals(799, actualResult);
    }

    @Test
    void testSignFunctionOnNegativeValue() {
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int actualResult = signFunction.apply(-123);

        assertEquals(-1, actualResult);
    }

    @Test
    void testSignFunctionOnPositiveValue() {
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int actualResult = signFunction.apply(23);

        assertEquals(1, actualResult);
    }

    @Test
    void testSignFunctionOnZero() {
        Function<Integer, Integer> signFunction = integerFunctionMap.getFunction("sgn");

        int actualResult = signFunction.apply(0);

        assertEquals(0, actualResult);
    }

    @Test
    void testGetUnknownFunction() {
        assertThrows(InvalidFunctionNameException.class, () -> integerFunctionMap.getFunction("sqrt"));
    }
}
