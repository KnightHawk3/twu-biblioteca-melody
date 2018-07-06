package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class UserInterfaceTest {
    private BibliotecaApp bib;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        bib = new BibliotecaApp();
    }

    @After
    public void restore() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void welcomeMessageTest() {
        final String input = "0";
        final String output = "Welcome to Biblioteca!\nPress any key to select an option.\n";
        setInput(input);
        BibliotecaApp.main(new String[0]);
        assertTrue(getOutput().contains(output));
    }

    @Test
    public void invalidInputShowsErrorTest() {
        final String input = "e\n0";
        final String output = "Welcome to Biblioteca!\n" +
                "Press any key to select an option.\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n" +
                "\n\n" +
                "Please select a number between 0 and 3.\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n\n";
        setInput(input);
        BibliotecaApp.main(new String[0]);
        assertEquals(output, getOutput());
    }

    @Test
    public void listBooksTest() {
        final String input = "1\n0";
        final String output = "Welcome to Biblioteca!\n" +
                "Press any key to select an option.\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n" +
                "\n" +
                "+-----------------------------------------+-----------------+------+\n" +
                "| Title                                   | Author          | Year |\n" +
                "+-----------------------------------------+-----------------+------+\n" +
                "| The Conquest of Bread                   | Peter Kropotkin | 1892 |\n" +
                "| Capital. Critique of Political Economy  | Karl Marx       | 1867 |\n" +
                "| The Ego and Its Own                     | Max Stirner     | 1845 |\n" +
                "+-----------------------------------------+-----------------+------+\n" +
                "\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n\n";
        setInput(input);
        BibliotecaApp.main(new String[0]);
        assertEquals(output, getOutput());
    }

    @Test
    public void checkoutBooksTest() {
        final String input = "2\nThe Conquest of Bread\n0";
        final String output = "Welcome to Biblioteca!\n" +
                "Press any key to select an option.\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n" +
                "\n" +
                "Please enter the name of the book to checkout: \n" +
                "Thank you! Enjoy the book.\n" +
                "\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n\n";
        setInput(input);
        BibliotecaApp.main(new String[0]);
        assertEquals(output, getOutput());
    }

    @Test
    public void returnBooksTest() {
        final String input = "2\nThe Conquest of Bread\n3\nThe Conquest of Bread\n0";
        final String output = "Welcome to Biblioteca!\n" +
                "Press any key to select an option.\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n" +
                "\n" +
                "Please enter the name of the book to checkout: \n" +
                "Thank you! Enjoy the book.\n" +
                "\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n" +
                "\n" +
                "Please enter the name of the book to return: \n" +
                "Thank you for returning the book.\n" +
                "\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n\n";
        setInput(input);
        BibliotecaApp.main(new String[0]);
        assertEquals(output, getOutput());
    }

    @Test
    public void checkoutFakeBookFailsTest() {
        final String input = "2\nThe cat in the hat\n0";
        final String output = "Welcome to Biblioteca!\n" +
                "Press any key to select an option.\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n" +
                "\n" +
                "Please enter the name of the book to checkout: \n" +
                "That book is not available.\n" +
                "\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n\n";
        setInput(input);
        BibliotecaApp.main(new String[0]);
        assertEquals(output, getOutput());
    }

    @Test
    public void checkoutTwiceFailsTest() {
        final String input = "2\nThe Conquest of Bread\n2\nThe Conquest of Bread\n0";
        final String output = "Welcome to Biblioteca!\n" +
                "Press any key to select an option.\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n" +
                "\n" +
                "Please enter the name of the book to checkout: \n" +
                "Thank you! Enjoy the book.\n" +
                "\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n" +
                "\n" +
                "Please enter the name of the book to checkout: \n" +
                "That book is not available.\n" +
                "\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n\n";
        setInput(input);
        BibliotecaApp.main(new String[0]);
        assertEquals(output, getOutput());
    }

    @Test
    public void returnNotCheckedOutBooksFailsTest() {
        final String input = "3\nThe Conquest of Bread\n0";
        final String output = "Welcome to Biblioteca!\n" +
                "Press any key to select an option.\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n" +
                "\n" +
                "Please enter the name of the book to return: \n" +
                "That is not a valid book to return.\n" +
                "\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n\n";
        setInput(input);
        BibliotecaApp.main(new String[0]);
        assertEquals(output, getOutput());
    }

    private String getOutput() {
        return outputStream.toString().replace("\r", "");
    }

    private void setInput(String data) {
        inputStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStream);
    }
}
