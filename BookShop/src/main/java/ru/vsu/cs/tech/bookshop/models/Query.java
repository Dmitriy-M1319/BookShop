package ru.vsu.cs.tech.bookshop.models;

import java.io.Serializable;

public class Query implements Serializable {
    private Long queryId;
    private String publishingHouse;
    private Book book;
    private Integer booksCount;
    private String status;

    public Query(String publishingHouse, Book book, Integer booksCount, String status) {
        this.publishingHouse = publishingHouse;
        this.book = book;
        this.booksCount = booksCount;
        this.status = status;
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
