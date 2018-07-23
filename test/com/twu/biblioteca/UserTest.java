package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserTest {

    private User mel;
    private User sarah;
    private Biblioteca bib;

    @Before
    public void setUp() {
        mel = new User("Melody Kelly",
                "melody@melody.blue",
                "555-555",
                "1234-1234",
                "banana");
        sarah = new User("Sarah B",
                "sarahb@gmail.com",
                "555-444",
                "2345-2345",
                "apple");
        bib = new Biblioteca();
    }

    @Test
    public void loginUserSucceedsTest() {
        assertTrue(mel.authenticate("banana"));
    }

    @Test
    public void loginUserFailsTest() {
        assertFalse(mel.authenticate("apple"));
    }

    @Test
    public void usersEqualTest() {
        assertTrue(new User("Melody Kelly",
                "melody@melody.blue",
                "555-555",
                "1234-1234",
                "banana").equals(mel));
    }

    @Test
    public void usersNotEqualTest() {
        assertFalse(mel.equals(sarah));
    }

    @Test
    public void findWhoHasItemTest() {
        bib.login("1234-1234", "banana");
        bib.checkoutItem("The Conquest of Bread");
        User has = bib.whoHasItem("The Conquest of Bread");
        assertTrue(has.equals(mel));
    }
}
