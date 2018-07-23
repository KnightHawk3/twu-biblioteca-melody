package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BibliotecaApp {

    private static Biblioteca library;
    private static Scanner input;
    private static PrintStream output = System.out;

    private static MenuItem[] generateMenuItems() {
        UserInterface ui = new UserInterface();
        return new MenuItem[]{
                new MenuItem("List books", () -> output.println(ui.listBooks(library))),
                new MenuItem("Checkout item", () -> {
                    output.print("Please enter the title of the item to checkout: \n");
                    output.println(ui.checkoutBook(library, input));
                }),
                new MenuItem("Return item", () -> {
                    output.print("Please enter the title of the item to return: \n");
                    output.println(ui.returnBook(library, input));
                }),
                new MenuItem("List movies", () -> output.println(ui.listMovies(library))),
                new MenuItem("See who checked out an item", () -> {
                    output.print("Please enter the title of the item you are interested in: ");
                    output.println(ui.whoHasItem(library, input));
                }),
                new MenuItem("Show my contact details", () -> output.println(ui.currentUser(library)))};
    }

    public static void main(String[] args) {
        main(System.out, System.in);
    }

    public static void main(PrintStream outputStream, InputStream inputStream) {
        output = outputStream;

        input = new Scanner(inputStream);
        library = new Biblioteca();

        String username;
        String password;
        try {
            output.print("Library Number: ");
            username = input.nextLine();
            output.print("Password: ");
            password = input.nextLine();
        } catch (NoSuchElementException e) {
            output.println("Please provide input on stdin");
            return;
        }
        library.login(username, password);
        Menu menu = new Menu(generateMenuItems());

        output.println("Welcome to Biblioteca!\nPress any key to select an option.");
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

}
