package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentLevel {
    public static List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(st -> Stream.ofNullable(st))
                .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
                .takeWhile(b -> b.getScore() > bound)
                .collect(Collectors.toList());
    }
}
