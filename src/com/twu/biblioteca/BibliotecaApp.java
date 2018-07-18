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
        UserInterface ui = new UserInterface();
        return new MenuItem[]{
                new MenuItem("List books", () -> output.println(ui.listBooks(library))),
                new MenuItem("Checkout book", () -> output.println(ui.checkoutBook(library, input))),
                new MenuItem("Return book", () -> output.println(ui.returnBook(library, input)))
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

}
