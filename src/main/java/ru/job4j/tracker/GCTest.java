package ru.job4j.tracker;

/**
 * Класс описывает тесты разных GC.
 * Используя разные ключи запуска виртуальной машины установить различные виды сборщика мусора.
 * Оценить поведение срабатывания различных типов сборщиков мусора.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class GCTest {
    public static void main(String[] args) {
        MemTracker tracker = new MemTracker();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            tracker.add(new Item(i, String.format("item %s", i + 1)));
        }
        long finish = System.currentTimeMillis();
        System.out.printf("Work time %d ms%n", (finish - start));
    }
}
