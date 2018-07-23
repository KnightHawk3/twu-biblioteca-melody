package com.twu.biblioteca;

import java.util.Scanner;

public class UserInterface {
    private StringBuilder output;

    public String returnBook(Biblioteca library, Scanner input) {
        output = new StringBuilder();
        if (library.returnItem(input.nextLine())) {
            output.append("Thank you for returning the item.\n");
        } else {
            output.append("That is not a valid item to return.\n");
        }
        return output.toString();
    }

    public String checkoutBook(Biblioteca library, Scanner input) {
        output = new StringBuilder();
        if (library.checkoutItem(input.nextLine())) {
            output.append("Thank you! Enjoy the item.\n");
        } else {
            output.append("That item is not available.\n");
        }
        return output.toString();
    }

    public String listBooks(Biblioteca library) {
        output = new StringBuilder();
        output.append(library.listBookDetails());
        return output.toString();
    }

    public String listMovies(Biblioteca library) {
        output = new StringBuilder();
        output.append(library.listMovieDetails());
        return output.toString();
    }

    public String whoHasItem(Biblioteca library, Scanner input) {
        output = new StringBuilder();
        User whom = library.whoHasItem(input.nextLine());
        if (whom != null) {
            output.append(whom.toString());
        } else {
            output.append("This is not a checked out item");
        }
        return output.toString();
    }

    public String currentUser(Biblioteca library) {
        User user = library.getCurrentUser();
        return user.toString();
    }
}
