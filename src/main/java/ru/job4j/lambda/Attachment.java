package ru.job4j.lambda;

import java.util.Comparator;

public class Attachment {
    private String name;
    private int size;

    public Attachment(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "{"
                + "name='" + name + '\''
                + ", size=" + size
                + '}';
    }

    Comparator<Attachment> compar = new Comparator<Attachment>() {
        @Override
        public int compare(Attachment o1, Attachment o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
}
