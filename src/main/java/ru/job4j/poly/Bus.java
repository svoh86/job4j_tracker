package ru.job4j.poly;

public class Bus implements Transport {

    @Override
    public void drive() {
        System.out.println("Bus can drive");
    }

    @Override
    public void passengers(int passengers) {
        System.out.println("Numbers of passengers " + passengers);
    }

    @Override
    public double refuel(double fuel) {
        double price = -1;
        return price * fuel;
    }
}
