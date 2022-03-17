package ru.job4j.stream.certificate;

import java.util.List;

/**
 * Класс Pupil описывает ученика
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Pupil {
    private String name;
    private List<Subject> subjects;

    public Pupil(String name, List<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}