package com.twu.biblioteca;

import java.util.Objects;

public class Item {
    private final String title;
    private final String creator;
    private final String tableFmt;
    private final String[] fieldTitles;
    private final int year;
    private boolean checkedOut = false;

    public Item(String title, String creator, int year, String tableFmt, String[] fieldTitles) {
        this.title = title;
        this.creator = creator;
        this.year = year;
        this.tableFmt = tableFmt;
        this.fieldTitles = fieldTitles;
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
    }

    public void checkout() {
        this.checkedOut = true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title);
    }

    public boolean equals(Book obj) {
        return (this.hashCode() == obj.hashCode());
    }

    public String getTableFmt() {
        return tableFmt;
    }

    public String[] getFieldTitles() {
        return fieldTitles;
    }
}

