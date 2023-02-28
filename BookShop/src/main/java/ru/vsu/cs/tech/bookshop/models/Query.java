package ru.vsu.cs.tech.bookshop.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
public class Query implements Serializable {
    @Id
    @GeneratedValue
    private Long queryId;
    @Column(name = "publishing_house")
    private String publishingHouse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;
    @Column(name = "books_count")
    private Integer booksCount;
    @Column
    private String status;

    public Query(String publishingHouse, Book book, Integer booksCount, String status) {
        this.publishingHouse = publishingHouse;
        this.book = book;
        this.booksCount = booksCount;
        this.status = status;
    }

    public Query() {

    }

    public void copyDataFromAnotherQuery(Query query) {
        this.publishingHouse = query.publishingHouse;
        this.book = query.book;
        this.booksCount = query.booksCount;
        this.status = query.status;
    }

    public Long getQueryId() {
        return queryId;
    }

    public void setQueryId(Long queryId) {
        this.queryId = queryId;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBooksCount() {
        return booksCount;
    }

    public void setBooksCount(Integer booksCount) {
        this.booksCount = booksCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
