package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int b) {
        return b - x;
    }

    public double divide(int c) {
        return c / x;
    }

    public double sumAllOperation(int d) {
        return sum(d) + multiply(d) + minus(d) + divide(d);
    }

    public static void main(String[] args) {
        int result = sum(10);
        System.out.println(result);
        Calculator calculator = new Calculator();
        int rsl = calculator.multiply(10);
        System.out.println(rsl);
        int min = minus(10);
        System.out.println(min);
        double div = calculator.divide(10);
        System.out.println(div);
        double sumAll = calculator.sumAllOperation(10);
        System.out.println(sumAll);
    }
}
