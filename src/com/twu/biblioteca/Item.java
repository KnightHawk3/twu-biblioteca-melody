package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Item {
    private final String title;
    private final String creator;
    private final List<String> header;
    private final int year;
    private boolean checkedOut = false;
    private String checkedOutLibraryNum;

    public Item(String title, String creator, int year, List<String> header) {
        this.title = title;
        this.creator = creator;
        this.year = year;
        this.header = header;
    }

    public List<String> getAsRow() {
        return Arrays.asList(
                this.getTitle(),
                this.getCreator(),
                Integer.toString(this.getYear()));
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    public int getYear() {
        return year;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void checkin() {
        this.checkedOut = false;
        this.checkedOutLibraryNum = null;
    }

    public String getCheckedOutLibraryNum() {
        return this.checkedOutLibraryNum;
    }

    public void checkout(User user) {
        this.checkedOut = true;
        this.checkedOutLibraryNum = user.getLibraryNumber();
    }

    public String[] toRow() {
        return new String[]{title, creator, Integer.toString(year)};
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.creator);
    }

    public boolean equals(Book obj) {
        return (this.hashCode() == obj.hashCode());
    }

    public List<String> getHeader() {
        return header;
    }
}

