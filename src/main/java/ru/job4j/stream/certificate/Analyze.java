package ru.job4j.stream.certificate;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс Analyze получает статистику по аттестатам.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Analyze {
    /**
     * Метод averageScore вычисляет общий средний балл.
     *
     * @param stream поток учеников
     * @return средний балл
     */
    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .flatMap(pupil -> pupil.getSubjects().stream())
                .mapToInt(Subject::getScore)
                .average()
                .orElse(0D);
    }

    /**
     * Метод вычисляет средний балл ученика по его предметам.
     *
     * @param stream поток учеников
     * @return список из объекта Tuple (имя ученика и средний балл)
     */
    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .map(pupil -> new Tuple(pupil.getName(),
                        pupil.getSubjects()
                                .stream()
                                .mapToInt(Subject::getScore)
                                .average()
                                .orElse(0D)))
                .collect(Collectors.toList());
    }

    /**
     * Метод вычисляет средний балл по всем предметам для каждого ученика.
     *
     * @param stream поток учеников
     * @return список из объекта Tuple (название предмета и средний балл).
     */
    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream
                .flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        LinkedHashMap::new,
                        Collectors.averagingDouble(Subject::getScore)
                ))
                .entrySet()
                .stream()
                .map(m -> new Tuple(m.getKey(), m.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Метод возвращает лучшего ученика. Лучшим считается ученик с наибольшим баллом по всем предметам.
     *
     * @param stream поток учеников
     * @return объект Tuple (имя ученика и сумма баллов по всем предметам)
     */
    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream
                .map(pupil -> new Tuple(pupil.getName(),
                        pupil.getSubjects()
                                .stream()
                                .mapToInt(Subject::getScore)
                                .sum()))
                .max((Comparator.comparingDouble(Tuple::getScore)))
                .orElse(new Tuple(" ", 0D));
    }

    /**
     * Метод возвращает предмет с наибольшим баллом для всех студентов.
     *
     * @param stream поток учеников
     * @return объект Tuple (имя предмета, сумма баллов каждого ученика по этому предмету)
     */
    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream
                .flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors.groupingBy(Subject::getName,
                        Collectors.summingDouble(Subject::getScore)
                ))
                .entrySet()
                .stream()
                .map(m -> new Tuple(m.getKey(), m.getValue()))
                .max(Comparator.comparingDouble(Tuple::getScore))
                .orElse(new Tuple(" ", 0D));
    }
}