package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookTest {
    private Book breadBook;
    private Book kapitalBook;
    private Biblioteca bib;

    @Before
    public void setUp() {
        breadBook = new Book("The Conquest of Bread", "Peter Kropotkin", 1892);
        kapitalBook = new Book("Capital. Critique of Political Economy", "Karl Marx", 1867);
        List<Book> library = new ArrayList<>();
        library.add(new Book("The Conquest of Bread", "Peter Kropotkin", 1892));
        library.add(new Book("Capital. Critique of Political Economy ", "Karl Marx", 1867));
        library.add(new Book("The Ego and Its Own", "Max Stirner", 1845));
        bib = new Biblioteca(library);
    }

    @Test
    public void listBookDetailsTest() {
        String expected =
                "+-----------------------------------------+-----------------+------+\n" +
                "| Title                                   | Author          | Year |\n" +
                "+-----------------------------------------+-----------------+------+\n" +
                "| The Conquest of Bread                   | Peter Kropotkin | 1892 |\n" +
                "| Capital. Critique of Political Economy  | Karl Marx       | 1867 |\n" +
                "| The Ego and Its Own                     | Max Stirner     | 1845 |\n" +
                "+-----------------------------------------+-----------------+------+\n";
        String books = bib.listBookDetails();
        assertEquals(expected, books);
    }

    @Test
    public void checkoutBookTest() {
        assertTrue(bib.checkoutBook("The Conquest of Bread"));
    }

    @Test
    public void listBookDetailsDoesntDisplayCheckedOutBooksTest() {
        String expected =
                "+-----------------------------------------+-----------------+------+\n" +
                        "| Title                                   | Author          | Year |\n" +
                        "+-----------------------------------------+-----------------+------+\n" +
                        "| The Conquest of Bread                   | Peter Kropotkin | 1892 |\n" +
                        "| Capital. Critique of Political Economy  | Karl Marx       | 1867 |\n" +
                        "+-----------------------------------------+-----------------+------+\n";
        bib.checkoutBook("The Ego and Its Own");
        String books = bib.listBookDetails();
        assertEquals(expected, books);
    }

    @Test
    public void returnBookTest() {
        bib.checkoutBook("The Conquest of Bread");
        assertTrue(bib.returnBook("The Conquest of Bread"));
    }

    @Test
    public void bookEqualityTest() {
        Book breadBook2 = new Book("The Conquest of Bread", "Peter Kropotkin", 1892);
        assertTrue(breadBook.equals(breadBook2));
        assertFalse(breadBook.equals(kapitalBook));
    }

    @Test
    public void bookHashTest() {
        Book breadBook2 = new Book("The Conquest of Bread", "Peter Kropotkin", 1892);
        assertEquals(breadBook.hashCode(), breadBook2.hashCode());
    }
}
