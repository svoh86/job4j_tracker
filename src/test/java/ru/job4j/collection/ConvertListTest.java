package ru.job4j.collection;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConvertListTest {

    @Test
    public void convert() {
        List<int[]> in = new ArrayList<>();
        in.add(new int[]{1});
        in.add(new int[]{2, 3});
        List<Integer> rsl = ConvertList.convert(in);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, rsl);
    }
}