package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class UserInterfaceTest {
    private BibliotecaApp bib;
    private ByteArrayOutputStream output;
    private ByteArrayInputStream input;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        bib = new BibliotecaApp();
    }

    @After
    public void restore() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    private String getOutput() {
        return output.toString();
    }

    private void setInput(String data) {
        input = new ByteArrayInputStream(data.getBytes());
        System.setIn(input);
    }
}
