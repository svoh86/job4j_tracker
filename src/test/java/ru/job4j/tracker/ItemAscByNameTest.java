package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ItemAscByNameTest {

    @Test
    public void whenSortAscending() {
        List<Item> items = Arrays.asList(
                new Item(4, "C"),
                new Item(2, "D"),
                new Item(1, "B"),
                new Item(3, "A")
        );
        Collections.sort(items, new ItemAscByName());
        List<Item> expected = Arrays.asList(
                new Item(3, "A"),
                new Item(1, "B"),
                new Item(4, "C"),
                new Item(2, "D")
        );
        assertEquals(expected, items);
    }

    @Test
    public void whenSortDescending() {
        List<Item> items = Arrays.asList(
                new Item(4, "C"),
                new Item(2, "D"),
                new Item(1, "B"),
                new Item(3, "A")
        );
        Collections.sort(items, new ItemDescByName());
        List<Item> expected = Arrays.asList(
                new Item(2, "D"),
                new Item(4, "C"),
                new Item(1, "B"),
                new Item(3, "A")
        );
        assertEquals(expected, items);
    }
}