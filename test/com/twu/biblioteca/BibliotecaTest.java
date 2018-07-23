package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.UserInterfaceTest.assertContainsAllWords;
import static org.junit.Assert.*;

public class BibliotecaTest {
    private Book breadBook;
    private Book kapitalBook;
    private Biblioteca bib;

    @Before
    public void setUp() {
        breadBook = new Book("The Conquest of Bread", "Peter Kropotkin", 1892);
        kapitalBook = new Book("Capital. Critique of Political Economy", "Karl Marx", 1867);
        bib = new Biblioteca();
        bib.login("1234-1234", "banana");
    }

    @Test
    public void listBookDetailsTest() {
        String expected = "┌──────────────────────────────────────┬───────────────┬────┐\n" +
                          "│Title                                 │Author         │Year│\n" +
                          "├──────────────────────────────────────┼───────────────┼────┤\n" +
                          "│The Conquest of Bread                 │Peter Kropotkin│1892│\n" +
                          "│Capital. Critique of Political Economy│Karl Marx      │1867│\n" +
                          "│The Ego and Its Own                   │Max Stirner    │1845│\n" +
                          "└──────────────────────────────────────┴───────────────┴────┘\n";
        String books = bib.listBookDetails();
        assertEquals(expected, books);
    }

    @Test
    public void listMovieDetailsTest() {
        String[] expected = {
                "Title", "Director", "Year", "Rating",
                "Memento", "Christopher Nolan", "2001", "7",
                "The Battle of Algiers", "Gillo Pontecorvo", "1966", "10",
                "Reds", "Warren Beatty", "1981", "7"
        };
        String books = bib.listMovieDetails();
        assertContainsAllWords(books, expected);
    }

    @Test
    public void checkoutBookTest() {
        assertTrue(bib.checkoutItem("The Conquest of Bread"));
    }

    @Test
    public void checkoutMovieTest() {
        assertTrue(bib.checkoutItem("Reds"));
    }

    @Test
    public void listBookDetailsDoesntDisplayCheckedOutBooksTest() {
        String expected = "┌──────────────────────────────────────┬───────────────┬────┐\n" +
                          "│Title                                 │Author         │Year│\n" +
                          "├──────────────────────────────────────┼───────────────┼────┤\n" +
                          "│The Conquest of Bread                 │Peter Kropotkin│1892│\n" +
                          "│Capital. Critique of Political Economy│Karl Marx      │1867│\n" +
                          "└──────────────────────────────────────┴───────────────┴────┘\n";
        bib.checkoutItem("The Ego and Its Own");
        String books = bib.listBookDetails();
        assertEquals(expected, books);
    }

    @Test
    public void returnBookTest() {
        bib.checkoutItem("The Conquest of Bread");
        assertTrue(bib.returnItem("The Conquest of Bread"));
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
