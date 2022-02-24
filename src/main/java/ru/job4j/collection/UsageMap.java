package ru.job4j.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> account = new HashMap<>();
        account.put("sms-86@mail.ru", "Svistunov");
        account.put("parsentev@yandex.ru", "Petr Arsentev");
        account.put("sms-86@mail.ru", "Svistunov");
        for (String key : account.keySet()) {
            String value = account.get(key);
            System.out.println(key + " - " + value);
        }
    }
}
