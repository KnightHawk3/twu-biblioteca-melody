package com.twu.biblioteca;

public class Menu {
    public int length() {
        return menuItems.length;
    }

    public class InvalidMenuSelectionException extends Exception {

        public InvalidMenuSelectionException(String message) {
            super(message);
        }
    }
    private boolean done;
    private MenuItem[] menuItems;

    public Menu(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    public boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < menuItems.length; i++) {
            output += String.format(" %d | %s\n", i + 1, menuItems[i].getTitle());
        }
        output += " 0 | Quit\n";
        return output;
    }

    public void input(String s) throws InvalidMenuSelectionException {
        if (s.equals("0")) {
            this.done = true;
        } else {
            // Subtracting one from the user input to match the array.
            try {
                this.menuItems[Integer.parseInt(s) - 1].callAction();
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new InvalidMenuSelectionException(String.format("Please select a number between 0 and %d.", menuItems.length));
            }
        }
    }
}
