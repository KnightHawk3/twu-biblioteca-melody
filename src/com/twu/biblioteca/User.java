package com.twu.biblioteca;

import java.util.Objects;

public class User {
    private String name;
    private String email;
    private String phone;
    private String libraryNumber;
    private String password;

    public User(String name, String email, String phone, String libraryNumber, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;

        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public String toString() {
        return String.format("Name: %s\nEmail: %s\nPhone: %s", this.name, this.email, this.phone);
    }

    public boolean authenticate(String password) {
        // This would normally be where we check a password hash etc but this isn't needed.
        return password.equals(this.password);
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.libraryNumber, this.name);
    }

    public boolean equals(Object other) {
        return this.hashCode() == other.hashCode();
    }
}
