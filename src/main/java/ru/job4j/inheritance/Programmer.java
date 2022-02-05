package ru.job4j.inheritance;

public class Programmer extends Engineer {

    private String lang;

    public Programmer(String name, String surname, String education, String birthday, int experience, String lang) {
        super(name, surname, education, birthday, experience);
        this.lang = lang;
    }

    public void program() {
    }
}
