package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CountFunctionTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        CountFunction function = new CountFunction();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSquareFunctionThenLinearResults() {
        CountFunction function = new CountFunction();
        List<Double> result = function.diapason(5, 8, x -> x * x + 2 * x + 1);
        List<Double> expected = Arrays.asList(36D, 49D, 64D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExpFunctionThenLinearResults() {
        CountFunction function = new CountFunction();
        List<Double> result = function.diapason(1, 4, x -> Math.pow(2, x));
        List<Double> expected = Arrays.asList(2D, 4D, 8D);
        assertThat(result, is(expected));
    }
}