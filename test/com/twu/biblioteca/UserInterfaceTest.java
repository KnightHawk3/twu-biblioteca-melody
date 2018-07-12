package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class UserInterfaceTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream ps;
    private InputStream inputStream;

    private void setInput(String input) {
        this.inputStream = new ByteArrayInputStream(input.getBytes());
    }

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        try {
            ps = new PrintStream(outputStream, true, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void noinputTest() {
        final String input = "";
        final String output = "Please provide a value\n";
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        assertTrue(getOutput().contains(output));
    }

    @Test
    public void welcomeMessageTest() {
        final String input = "0";
        final String output = "Welcome to Biblioteca!\nPress any key to select an option.\n";
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
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
                "Select a valid option!\n" +
                " 1 | List books\n" +
                " 2 | Checkout book\n" +
                " 3 | Return book\n" +
                " 0 | Quit\n\n";
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
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
        BibliotecaApp.main(ps, inputStream);
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
        BibliotecaApp.main(ps, inputStream);
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
        BibliotecaApp.main(ps, inputStream);
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
        BibliotecaApp.main(ps, inputStream);
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
        BibliotecaApp.main(ps, inputStream);
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
        BibliotecaApp.main(ps, inputStream);
        assertEquals(output, getOutput());
    }

    private String getOutput() {
        try {
            return outputStream.toString("UTF-8").replace("\r", "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return e.getStackTrace().toString();
        }
    }
}
