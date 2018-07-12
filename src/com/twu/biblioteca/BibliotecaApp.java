package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BibliotecaApp {

    private static Biblioteca library;
    private static Scanner input;

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca!\nPress any key to select an option.");
        input = new Scanner(System.in);
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Conquest of Bread", "Peter Kropotkin", 1892));
        books.add(new Book("Capital. Critique of Political Economy ", "Karl Marx", 1867));
        books.add(new Book("The Ego and Its Own", "Max Stirner", 1845));
        library = new Biblioteca(books);

        MenuItem[] menuItems = {
                new MenuItem("List books", BibliotecaApp::listBooks),
                new MenuItem("Checkout book", BibliotecaApp::checkoutBook),
                new MenuItem("Return book", BibliotecaApp::returnBook)
        };

        Menu menu = new Menu(menuItems);
        while (!menu.isDone()) {
            try {
                System.out.println(menu);
                menu.input(input.nextLine().replace("\n", ""));
            } catch (Menu.InvalidMenuSelectionException e) {
                System.out.println("\n" + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Please provide a value");
                break;
            }
        }
    }

    private static void returnBook() {
        System.out.println("Please enter the name of the book to return: ");
        if (library.returnBook(input.nextLine())) {
            System.out.println("Thank you for returning the book.\n");
        } else {
            System.out.println("That is not a valid book to return.\n");
        }
    }

    private static void checkoutBook() {
        System.out.println("Please enter the name of the book to checkout: ");
        if (library.checkoutBook(input.nextLine())) {
            System.out.println("Thank you! Enjoy the book.\n");
        } else {
            System.out.println("That book is not available.\n");
        }
    }

    public static void listBooks() {
        System.out.println(library.listBookDetails());
    }
}
