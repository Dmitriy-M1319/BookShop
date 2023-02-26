package ru.vsu.cs.tech.bookshop.models;

import java.io.Serializable;

public class OrderBook implements Serializable {
    private Long id;
    private Order order;
    private String bookName;
    private String bookAuthor;
    private String bookTag;
    private Integer booksCount;

    public OrderBook(Order order, String bookName, String bookAuthor, String bookTag, Integer booksCount) {
        this.order = order;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookTag = bookTag;
        this.booksCount = booksCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookTag() {
        return bookTag;
    }

    public void setBookTag(String bookTag) {
        this.bookTag = bookTag;
    }

    public Integer getBooksCount() {
        return booksCount;
    }

    public void setBooksCount(Integer booksCount) {
        this.booksCount = booksCount;
    }
}
