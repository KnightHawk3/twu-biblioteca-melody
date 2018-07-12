package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BibliotecaApp {

    private static Biblioteca library;
    private static Scanner input;
    private static PrintStream output = System.out;

    private static List<Item> generateItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Book("The Conquest of Bread", "Peter Kropotkin", 1892));
        items.add(new Book("Capital. Critique of Political Economy ", "Karl Marx", 1867));
        items.add(new Book("The Ego and Its Own", "Max Stirner", 1845));
        return items;
    }

    private static MenuItem[] generateMenuItems() {
        return new MenuItem[]{
                new MenuItem("List books", BibliotecaApp::listBooks),
                new MenuItem("Checkout book", BibliotecaApp::checkoutBook),
                new MenuItem("Return book", BibliotecaApp::returnBook)
        };
    }

    public static void main(String[] args) {
        main(System.out, System.in);
    }

    public static void main(PrintStream outputStream, InputStream inputStream) {
        output = outputStream;
        output.println("Welcome to Biblioteca!\nPress any key to select an option.");

        input = new Scanner(inputStream);
        library = new Biblioteca(generateItems());
        Menu menu = new Menu(generateMenuItems());

        while (!menu.isDone()) {
            try {
                output.println(menu);
                menu.input(input.nextLine().replace("\n", ""));
            } catch (Menu.InvalidMenuSelectionException e) {
                output.println("\n" + e.getMessage());
            } catch (NoSuchElementException e) {
                output.println("Please provide a value");
                break;
            }
        }
    }

    private static void returnBook() {
        output.println("Please enter the name of the book to return: ");
        if (library.returnBook(input.nextLine())) {
            output.println("Thank you for returning the book.\n");
        } else {
            output.println("That is not a valid book to return.\n");
        }
    }

    private static void checkoutBook() {
        output.println("Please enter the name of the book to checkout: ");
        if (library.checkoutBook(input.nextLine())) {
            output.println("Thank you! Enjoy the book.\n");
        } else {
            output.println("That book is not available.\n");
        }
    }

    public static void listBooks() {
        output.println(library.listBookDetails());
    }
}
