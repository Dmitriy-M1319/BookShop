package ru.vsu.cs.tech.bookshop.models;

import java.util.Comparator;

public class BookRatingComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getRating().compareTo(o2.getRating());
    }
}
