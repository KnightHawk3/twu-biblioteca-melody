package com.twu.biblioteca;

import java.util.Scanner;

public class UserInterface {
    private StringBuilder output;

    public String returnBook(Biblioteca library, Scanner input) {
        output = new StringBuilder();
        output.append("Please enter the name of the book to return: \n");
        if (library.returnBook(input.nextLine())) {
            output.append("Thank you for returning the book.\n");
        } else {
            output.append("That is not a valid book to return.\n");
        }
        return output.toString();
    }

    public String checkoutBook(Biblioteca library, Scanner input) {
        output = new StringBuilder();
        output.append("Please enter the name of the book to checkout: \n");
        if (library.checkoutBook(input.nextLine())) {
            output.append("Thank you! Enjoy the book.\n");
        } else {
            output.append("That book is not available.\n");
        }
        return output.toString();
    }

    public String listBooks(Biblioteca library) {
        output = new StringBuilder();
        output.append(library.listBookDetails());
        return output.toString();
    }
}
