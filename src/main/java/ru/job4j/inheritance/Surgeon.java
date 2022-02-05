package ru.job4j.inheritance;

public class Surgeon extends Doctor {

    private String category;

    public Surgeon(String name, String surname, String education, String birthday, char gender, String category) {
        super(name, surname, education, birthday, gender);
        this.category = category;
    }

    public void operation() {

    }
}
