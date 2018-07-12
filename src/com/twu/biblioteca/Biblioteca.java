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
        StringBuilder output = new StringBuilder();
        int titleWidth = 0;
        int authorWidth = 0;
        int yearWidth = 0;

        // Find out column widths
        for (Book book : getLibrary()) {
            if (!book.isCheckedOut()) {
                if (book.getTitle().length() > titleWidth) {
                    titleWidth = book.getTitle().length();
                }
                if (book.getAuthor().length() > authorWidth) {
                    authorWidth = book.getAuthor().length();
                }
                if (book.getYear() > yearWidth) {
                    yearWidth = String.valueOf(book.getYear()).length();
                }
            }
        }

        // Build formatting strings for table
        String linebreak = "";
        String bookLine = "";

        // Please take a moment to ponder why "".repeat(5) is going to be released in JDK 11.
        linebreak += "+" + String.join("", Collections.nCopies(titleWidth + 2, "-")) + "+";
        linebreak += String.join("", Collections.nCopies(authorWidth + 2, "-")) + "+";
        linebreak += String.join("", Collections.nCopies(yearWidth + 2, "-")) + "+\n";
        bookLine += "| %-" + titleWidth + "s | %-" + authorWidth + "s | %-" + yearWidth + "s |\n";

        // Build the table
        output.append(linebreak);
        output.append(String.format(bookLine, "Title", "Author", "Year"));
        output.append(linebreak);
        for (Book book : getLibrary()) {
            if (!book.isCheckedOut()) {
                output.append(String.format(bookLine, book.getTitle(), book.getAuthor(), book.getYear()));
            }
        }
        output.append(linebreak);
        return output.toString();
    }

    public boolean checkoutBook(String title) {
        for (Book book : library) {
            if (book.getTitle().equals(title)) {
                if (!book.isCheckedOut()) {
                    book.checkout();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean returnBook(String title) {
        for (Book book : library) {
            if (book.getTitle().equals(title)) {
                if (book.isCheckedOut()) {
                    book.checkin();
                    return true;
                }
            }
        }
        return false;
    }
}
