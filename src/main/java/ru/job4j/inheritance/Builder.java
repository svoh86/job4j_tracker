package ru.job4j.inheritance;

public class Builder extends Engineer {

    private String specialization;

    public Builder(String name, String surname, String education, String birthday, int experience, String specialization) {
        super(name, surname, education, birthday, experience);
        this.specialization = specialization;
    }

    public void house() {
    }
}
