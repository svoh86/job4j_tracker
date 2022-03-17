package ru.job4j.stream.certificate;

/**
 * Класс Subject описывает школьный предмет и аттестационный балл ученика.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Subject {
    private String name;
    private int score;

    public Subject(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}