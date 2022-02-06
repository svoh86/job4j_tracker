package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("First", 100);
        Book book2 = new Book("Second", 200);
        Book book3 = new Book("Third", 300);
        Book book4 = new Book("Four", 400);
        book2.setName("Clean code");
        Book[] books = new Book[4];
        books[0] = book1;
        books[1] = book2;
        books[2] = book3;
        books[3] = book4;
        for (int i = 0; i < books.length; i++) {
            Book b = books[i];
            System.out.println(b.getName() + " " + b.getPages());
        }
        Book temp;
        temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (int i = 0; i < books.length; i++) {
            Book b = books[i];
            System.out.println(b.getName() + " " + b.getPages());
        }
        for (int i = 0; i < books.length; i++) {
            if (books[i].getName().equals("Clean code")) {
                System.out.println(books[i].getName());
            }
        }
    }
}
