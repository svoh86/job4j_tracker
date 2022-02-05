package ru.job4j.inheritance;

public class Dentist extends Doctor {

    private String specialization;

    public Dentist(String name, String surname, String education, String birthday, char gender, String specialization) {
        super(name, surname, education, birthday, gender);
        this.specialization = specialization;
    }

    public void tooth() {
    }
}
