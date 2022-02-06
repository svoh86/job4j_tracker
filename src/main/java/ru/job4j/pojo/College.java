package ru.job4j.pojo;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setSurname("Ivanov");
        student.setGroup("A-6");
        student.setDate("01.09.21");
        System.out.println("Фамилия: " + student.getSurname() + ", группа: " + student.getGroup()
                + ", дата поступления: " + student.getDate());
    }
}
