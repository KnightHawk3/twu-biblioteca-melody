package com.twu.biblioteca;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Biblioteca {
    private List<Item> library;
    private List<User> users;
    private User currentUser;

    public Biblioteca() {
        this(Arrays.asList(
                new Book("The Conquest of Bread", "Peter Kropotkin", 1892),
                new Book("Capital. Critique of Political Economy", "Karl Marx", 1867),
                new Book("The Ego and Its Own", "Max Stirner",1845 ),
                new Movie("Memento", "Christopher Nolan", 2001, 7),
                new Movie("The Battle of Algiers", "Gillo Pontecorvo", 1966, 10),
                new Movie("Reds", "Warren Beatty", 1981, 7)
        ), Arrays.asList(
                new User("Melody Kelly",
                        "melody@melody.blue",
                        "555-555",
                        "1234-1234",
                        "banana"),
                new User("Sarah B",
                        "sarahb@gmail.com",
                        "555-444",
                        "2345-2345",
                        "apple")));
    }

    public Biblioteca(List<Item> library, List<User> users) {
        this.library = library;
        this.users = users;
    }

    public List<Item> getLibrary(Class<?> cls) {
        List<Item> output = new ArrayList<>();
        for (Item item : library) {
            if (item.getClass() == cls) {
                output.add(item);
            }
        }
        return output;
    }

    public List<Item> getLibrary() {
        return library;
    }

    public String createTable(List<Item> items) {
        /*
            This should only take one type of Item (Book or Movie).
         */

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow(items.get(0).getHeader());
        at.addRule();
        for (Item item : items) {
            at.addRow(item.getAsRow());
        }
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestLine());
        return at.render() + "\n";
    }

    public String listBookDetails() {
        List<Item> lib = getLibrary(Book.class);

        for (int index = 0; index < lib.size(); index++) {
            if (lib.get(index).isCheckedOut()) {
                lib.remove(index);
                index--; // Subtract one form the index to avoid IndexOutOfBoundsError.
            }
        }
        return createTable(lib);
    }

    public String listMovieDetails() {
        List<Item> lib = getLibrary(Movie.class);

        for (int index = 0; index < lib.size(); index++) {
            if (lib.get(index).isCheckedOut()) {
                lib.remove(index);
                index--; // Subtract one form the index to avoid IndexOutOfBoundsError.
            }
        }
        return createTable(lib);
    }

    public boolean checkoutItem(String title) {
        for (Item item : library) {
            if (item.getTitle().equals(title)) {
                if (!item.isCheckedOut()) {
                    item.checkout(this.currentUser);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean returnItem(String title) {
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

    public User whoHasItem(String title) {
        Item item = null;
        for (Item index : library) {
            if (index.getTitle().equals(title)) {
                item = index;
            }
        }
        if (item == null) {
            return null;
        } else if (!item.isCheckedOut()) {
            return null;
        }
        for (User user : users) {
            if (user.getLibraryNumber().equals(item.getCheckedOutLibraryNum())) {
                return user;
            }
        }
        return null;
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getLibraryNumber().equals(username)) {
                if (user.authenticate(password)) {
                    this.currentUser = user;
                    return true;
                }
            }
        }
        return false;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }
}
