package com.twu.biblioteca;

import java.util.Objects;

public class Book extends Item {

    public Book(String title, String creator, int year) {
        super(title, creator, year,
                "| %%-%ss | %%-%ss | %%-%ss |\n",
                new String[]{"Title", "Author", "Year"});
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTitle(), this.getCreator(), this.getYear());
    }
}
