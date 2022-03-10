package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> first = f -> f.getName().contains(key);
        Predicate<Person> second = s -> s.getSurname().contains(key);
        Predicate<Person> third = t -> t.getPhone().contains(key);
        Predicate<Person> fourth = fo -> fo.getAddress().contains(key);
        var combine = first.or(second).or(third).or(fourth);
        var result = new ArrayList<Person>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}