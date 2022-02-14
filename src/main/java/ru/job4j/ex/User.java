package ru.job4j.ex;

import java.util.Objects;

public class User {
    private String username;
    private boolean valid;

    public User(String name, boolean valid) {
        this.username = name;
        this.valid = valid;
    }

    public String getUsername() {
        return username;
    }

    public boolean isValid() {
        return valid;
    }
}
