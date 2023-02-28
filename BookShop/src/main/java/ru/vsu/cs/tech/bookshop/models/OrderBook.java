package ru.vsu.cs.tech.bookshop.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
public class OrderBook implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Order order;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "book_author")
    private String bookAuthor;
    @Column(name = "book_tag")
    private String bookTag;
    @Column(name = "book_count")
    private Integer booksCount;

    public OrderBook(Order order, String bookName, String bookAuthor, String bookTag, Integer booksCount) {
        this.order = order;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookTag = bookTag;
        this.booksCount = booksCount;
    }

    public OrderBook() {

    }

    public void copyDataFromAnotherOrderBook(OrderBook book) {
        this.order = book.order;
        this.bookName = book.bookName;
        this.bookAuthor = book.bookAuthor;
        this.bookTag = book.bookTag;
        this.booksCount = book.booksCount;
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
