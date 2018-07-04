package com.twu.biblioteca;

import java.util.Date;
import java.util.Objects;

public class Book {
    private final String title;
    private final String author;
    private final Date published;


    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.author);
    }

    public Book(String title, String author, Date published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.published = new Date(year, 0, 0);
    }

    public boolean equals(Book obj) {
        // I could refactor this to just use hashCode()
        return (this.getTitle().equals(obj.getTitle())
                && this.getAuthor().equals(obj.getAuthor())
                && this.getPublished().equals(obj.getPublished()));
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublished() {
        return published;
    }
}
