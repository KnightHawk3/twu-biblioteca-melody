package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Biblioteca {

    public List<Book> listBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("The Conquest of Bread", "Peter Kropotkin", new Date(1892)));
        books.add(new Book("Capital. Critique of Political Economy ", "Karl Marx", new Date(1867)));
        books.add(new Book("The Ego and Its Own", "Max Stirner", new Date(1845)));
        return books;
    }
}
