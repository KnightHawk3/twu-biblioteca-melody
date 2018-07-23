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
    private final String loginString = "1234-1234\nbanana\n";

    private void setInput(String input) {
        this.inputStream = new ByteArrayInputStream(input.getBytes());
    }
    public static void assertContainsAllWords(String output, String ...keywords) {
        for (String keyword : keywords) {
            assertTrue(output.contains(keyword));
        }
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
        final String output = "Please provide input on stdin\n";
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        assertTrue(getOutput().contains(output));
    }

    @Test
    public void loginTest() {
        final String input = "1234-1234\nbanana\n0";
        final String output = "Library Number: Password: Welcome to Biblioteca!\nPress any key to select an option.\n";
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        String out = getOutput();
        assertTrue(getOutput().contains(output));
    }

    @Test
    public void invalidInputShowsErrorTest() {
        final String input = loginString + "e\n0";
        final String output = "Library Number: Password: Welcome to Biblioteca!\n" +
                "Press any key to select an option.\n" +
                " 1 | List books\n" +
                " 2 | Checkout item\n" +
                " 3 | Return item\n" +
                " 4 | List movies\n" +
                " 5 | See who checked out an item\n" +
                " 6 | Show my contact details\n" +
                " 0 | Quit\n" +
                "\n\n" +
                "Select a valid option!\n" +
                " 1 | List books\n" +
                " 2 | Checkout item\n" +
                " 3 | Return item\n" +
                " 4 | List movies\n" +
                " 5 | See who checked out an item\n" +
                " 6 | Show my contact details\n" +
                " 0 | Quit\n\n";
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        assertEquals(output, getOutput());
    }

    @Test
    public void listBooksTest() {
        final String input = loginString + "1\n0";
        final String[] output = {
                "The Conquest of Bread", "Peter Kropotkin", "1892",
                "Capital. Critique of Political Economy", "Karl Marx", "1867",
                "The Ego and Its Own", "Max Stirner", "1845"
        };
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        assertContainsAllWords(getOutput(), output);
    }

    @Test
    public void checkoutBooksTest() {
        final String input = loginString + "2\nThe Conquest of Bread\n0";
        final String[] output = new String[] {
                "Please enter the title of the item to checkout: \n",
                "Thank you! Enjoy the item.\n"
        };
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        assertContainsAllWords(getOutput(), output);
    }

    @Test
    public void returnBooksTest() {
        final String input = loginString + "2\nThe Conquest of Bread\n3\nThe Conquest of Bread\n0";
        final String[] output = new String[]{
                "Please enter the title of the item to return:",
                "Thank you for returning the item."
        };
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        String out = getOutput();
        assertContainsAllWords(getOutput(), output);
    }

    @Test
    public void checkoutFakeBookFailsTest() {
        final String input = loginString + "2\nThe cat in the hat\n0";
        final String output = "That item is not available.\n";
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        assertContainsAllWords(getOutput(), output);
    }

    @Test
    public void checkoutTwiceFailsTest() {
        final String input = loginString + "2\nThe Conquest of Bread\n2\nThe Conquest of Bread\n0";
        final String[] output = new String[]{
                "Thank you! Enjoy the item.",
                "That item is not available."
        };
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        assertContainsAllWords(getOutput(), output);
    }

    @Test
    public void returnNotCheckedOutItemsFailsTest() {
        final String input = loginString + "3\nThe Conquest of Bread\n0";
        final String output = "That is not a valid item to return.";
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        assertContainsAllWords(getOutput(), output);
    }

    @Test
    public void listMoviesTest() {
        final String input = loginString + "4\n0";
        final String[] output = new String[]{
                "Memento", "Nolan", "Battle of Algiers", "1966", "10", "Reds"
        };
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        assertContainsAllWords(getOutput(), output);
    }

    @Test
    public void getWhoCheckedOutBookTest() {
        final String input = loginString + "2\nThe Conquest of Bread\n5\nThe Conquest of Bread\n0";
        final String[] output = new String[]{
                "Melody Kelly",
                "melody@melody.blue",
                "555-555"
        };
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        assertContainsAllWords(getOutput(), output);
    }

    @Test
    public void getCurrentUserTest() {
        final String input = loginString + "6\n0";
        final String[] output = new String[]{
                "Melody Kelly",
                "melody@melody.blue",
                "555-555"
        };
        setInput(input);
        BibliotecaApp.main(ps, inputStream);
        assertContainsAllWords(getOutput(), output);
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
