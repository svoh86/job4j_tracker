package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemSorter {
    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
                new Item(4, "C"),
                new Item(2, "D"),
                new Item(1, "B"),
                new Item(3, "A")
        );
        System.out.println(items);
        Collections.sort(items, new ItemAscByName());
        items.sort(new ItemAscByName());
        System.out.println(items);
        Collections.sort(items, new ItemDescByName());
        items.sort(new ItemDescByName());
        System.out.println(items);
    }
}

