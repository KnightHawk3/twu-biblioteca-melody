package com.twu.biblioteca;

public class MenuItem {
    private final String title;
    private final Runnable action;

    public MenuItem(String title, Runnable action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void callAction() {
        this.action.run();
    }
}
