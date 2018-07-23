package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Movie extends Item {

    private int rating;

    public Movie(String title, String creator, int year, int rating) {
        super(title, creator, year,
                Arrays.asList("Title", "Director", "Year", "Rating"));
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException();
        } else {
            this.rating = rating;
        }
    }

    @Override
    public List<String> getAsRow() {
        return Arrays.asList(
                this.getTitle(),
                this.getCreator(),
                Integer.toString(this.getYear()),
                Integer.toString(this.getRating()));
    }

    private int getRating() {
        return rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTitle(), this.getCreator(), this.getYear());
    }
}
