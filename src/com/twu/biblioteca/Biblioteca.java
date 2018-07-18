package com.twu.biblioteca;

import java.util.*;

public class Biblioteca {
    private List<Item> library;

    public Biblioteca(List<Item> library) {
        this.library = library;
    }

    public List<Item> getLibrary(Class<?> cls) {
        List<Item> output = new ArrayList<>();
        for (Item item : library) {
            if (item.getClass() == cls) {
                output.add(item);
            }
        }
        return library;
    }

    public List<Item> getLibrary() {
        return library;
    }

    public String createTable(List<Item> items, AbstractMap.SimpleImmutableEntry<String, Integer>... columns) {
        /*
            This should only take one type of Item (Book or Movie).
         */
        StringBuilder output = new StringBuilder();
        // Build formatting strings for table
        String itemLine = "";

        String linebreak = "+";
        for (AbstractMap.SimpleImmutableEntry<String, Integer> col : columns) {
            linebreak += String.join("", Collections.nCopies(col.getValue() + 2, "-")) + "+";
        }
        linebreak += "\n";

        itemLine = String.format(items.get(0).getTableFmt(), Arrays.stream(columns).map(e -> e.getValue()).toArray());

        // Build the table
        output.append(linebreak);
        output.append(String.format(itemLine, Arrays.stream(columns).map(e -> e.getKey()).toArray()));
        output.append(linebreak);
        for (Item item : getLibrary()) {
            output.append(String.format(itemLine, item.getTitle(), item.getCreator(), item.getYear()));
        }
        output.append(linebreak);
        return output.toString();
    }

    public String listBookDetails() {
        int titleWidth = 0;
        int authorWidth = 0;
        int yearWidth = 0;

        List<Item> lib = getLibrary(Book.class);

        for (int index = 0; index < lib.size(); index++) {
            if (lib.get(index).isCheckedOut()) {
                lib.remove(index);
                index--; // Subtract one form the index to avoid IndexOutOfBoundsError.
            }
        }

        // Find out column widths
        for (Item book : getLibrary(Book.class)) {
            if (!book.isCheckedOut()) {
                if (book.getTitle().length() > titleWidth) {
                    titleWidth = book.getTitle().length();
                }
                if (book.getCreator().length() > authorWidth) {
                    authorWidth = book.getCreator().length();
                }
                if (book.getYear() > yearWidth) {
                    yearWidth = String.valueOf(book.getYear()).length();
                }
            }
        }

        String output = createTable(lib,
                new AbstractMap.SimpleImmutableEntry<>("Title", titleWidth),
                new AbstractMap.SimpleImmutableEntry<>("Author", authorWidth),
                new AbstractMap.SimpleImmutableEntry<>("Year", yearWidth));

        return output;
    }

    public boolean checkoutBook(String title) {
        for (Item item : library) {
            if (item.getTitle().equals(title)) {
                if (!item.isCheckedOut()) {
                    item.checkout();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean returnBook(String title) {
        for (Item item : library) {
            if (item.getTitle().equals(title)) {
                if (item.isCheckedOut()) {
                    item.checkin();
                    return true;
                }
            }
        }
        return false;
    }
}
