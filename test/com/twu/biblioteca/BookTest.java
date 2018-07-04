package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookTest {
    private Book breadBook;
    private Book kapitalBook;

    @Before
    public void setUp() {
        breadBook = new Book("The Conquest of Bread", "Peter Kropotkin", 1892);
        kapitalBook = new Book("Capital. Critique of Political Economy", "Karl Marx", 1867);

    }

    @Test
    public void listBookTest() {
        List<Book> expected = new ArrayList<Book>();
        expected.add(new Book("The Conquest of Bread", "Peter Kropotkin", 1892));
        expected.add(new Book("Capital. Critique of Political Economy ", "Karl Marx", 1867));
        expected.add(new Book("The Ego and Its Own", "Max Stirner", 1845));
        Biblioteca bib = new Biblioteca();
        List<Book> books = bib.listBooks();
        assertThat(books, is(expected));
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
