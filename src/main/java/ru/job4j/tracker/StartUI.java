package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StartUI {
    public static void main(String[] args) {
        Item item = new Item();
        LocalDateTime date = item.getCreated();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        String currentDate = date.format(dateTimeFormatter);
        System.out.println(currentDate);
        item.setName("ttt");
        item.setId(33);
        System.out.println(item);
    }
}
