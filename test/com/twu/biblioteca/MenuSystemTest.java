package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuSystemTest {

    private Menu main;
    private int firstItem;
    private int secondItem;
    private int thirdItem;

    private void iterateFirstItem() {
        this.firstItem++;
    }

    private void iterateSecondItem() {
        this.secondItem++;
    }

    private void iterateThirdItem() {
        this.thirdItem++;
    }

    @Before
    public void setUp() {
        firstItem = 0;
        secondItem = 0;
        thirdItem = 0;
        MenuItem[] menuItems = {
                new MenuItem("List books", this::iterateFirstItem),
                new MenuItem("Checkout book", this::iterateSecondItem),
                new MenuItem("Return book", this::iterateThirdItem)
        };
        this.main = new Menu(menuItems);
    }


    @Test
    public void testMenuQuits() {
        assertFalse(main.isDone());
        try {
            main.input("0"); // Check quitting works
        } catch (Exception e) {
        }
        assertTrue(main.isDone());
    }

    @Test
    public void testMenuCallsFirstItem() {
        try {
            main.input("1");
        } catch (Exception e) {
        }
        assertEquals(firstItem, 1);
        assertEquals(secondItem, 0);
        assertEquals(thirdItem, 0);
    }

    @Test
    public void testMenuPrintsCorrectly() {
        String expectedOutput =
                " 1 | List books\n" +
                        " 2 | Checkout book\n" +
                        " 3 | Return book\n" +
                        " 0 | Quit\n";
        assertEquals(expectedOutput, main.toString());
    }

    @Test
    public void testInvalidSelection() {
        String expectedOutput = "Select a valid option!";
        boolean exceptionThrown;
        try {
            main.input("9");
            exceptionThrown = false;
        } catch (Menu.InvalidMenuSelectionException e) {
            assertEquals(e.getMessage(), expectedOutput);
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void testInvalidInput() {
        String expectedOutput = "Select a valid option!";
        boolean exceptionThrown;
        try {
            main.input("e");
            exceptionThrown = false;
        } catch (Menu.InvalidMenuSelectionException e) {
            assertEquals(e.getMessage(), expectedOutput);
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void testMenuLength() {
        MenuItem[] menuItems = {
                new MenuItem("List books", this::iterateFirstItem),
                new MenuItem("Checkout book", this::iterateSecondItem),
        };
        Menu menu = new Menu(menuItems);
        assertEquals(main.length(), 3);
        assertEquals(menu.length(), 2);
    }

}
