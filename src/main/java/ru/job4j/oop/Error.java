package ru.job4j.oop;

public class Error {

    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void Print() {
        System.out.println(active);
        System.out.println(status);
        System.out.println(message);
    }

    public static void main(String[] args) {
        Error error = new Error();
        Error error1 = new Error(true, 1, "Ошибка");
        Error error2 = new Error(false, 0, "Нет ошибки");
        error.Print();
        error1.Print();
        error2.Print();
    }
}
