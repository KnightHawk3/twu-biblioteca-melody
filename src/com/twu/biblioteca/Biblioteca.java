package com.twu.biblioteca;

import java.util.Collections;
import java.util.List;

public class Biblioteca {
    private List<Book> library;

    public Biblioteca(List<Book> library) {
        this.library = library;
    }

    public List<Book> getLibrary() {
        return library;
    }

    public String listBookDetails() {
        String output = "";
        int titleWidth = 0;
        int authorWidth = 0;
        int yearWidth = 0;
        for (Book book : getLibrary()) {
            if (book.isCheckedOut()) {
                break;
            }
            if (book.getTitle().length() > titleWidth) {
                titleWidth = book.getTitle().length();
            }
            if (book.getAuthor().length() > authorWidth) {
                authorWidth = book.getTitle().length();
            }
            if (book.getYear() > yearWidth) {
                yearWidth = book.getTitle().length();
            }
        }
        output += String.join("", Collections.nCopies(titleWidth / 2, " "));
        output += "Title";
        output += String.join("", Collections.nCopies(titleWidth / 2, " "));
        output += String.join("", Collections.nCopies(authorWidth / 2, " "));
        output += "Author";
        output += String.join("", Collections.nCopies(authorWidth / 2, " "));
        for (Book book : getLibrary()) {
            if (book.isCheckedOut()) {
                break;
            }
        }
        return output;
        /*
        return  "                  Title                        Author        Year  \n" +
                " ---------------------------------------- ----------------- ------ \n" +
                "  The Conquest of Bread                    Peter Kropotkin   1892  \n" +
                "  Capital. Critique of Political Economy   Karl Marx         1867  \n" +
                "  The Ego and Its own                      Max Stirner       1845  \n";
                */
    }

    public boolean checkout(String book) {
        return true;
    }
}
