package com.twu.biblioteca;

import java.util.Objects;

public class Book {
    private final String title;
    private final String author;
    private final int year;
    private boolean checkedOut = false;


    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.author);
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public boolean equals(Book obj) {
        return (this.hashCode() == obj.hashCode());
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void checkin() {
        this.checkedOut = false;
    }

    public void checkout() {
        this.checkedOut = true;
    }
}
