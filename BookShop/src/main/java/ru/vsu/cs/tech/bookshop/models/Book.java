package ru.vsu.cs.tech.bookshop.models;

import java.io.Serializable;

public class Book implements Serializable {

    private Long bookId;
    private BookCategory category;
    private String author;
    private String name;
    private String publishingHouse;
    private Integer publishYear;
    private Integer pagesCount;
    private Integer price;
    private Integer retailMargin;
    private Boolean availability;
    private Integer countInShop;
    private String status;
    private Float rating;

    public Book(BookCategory category,
                String author,
                String name,
                String publishingHouse,
                Integer publishYear,
                Integer pagesCount,
                Integer price,
                Integer retailMargin,
                Boolean availability,
                Integer countInShop,
                String status,
                Float rating) {
        this.category = category;
        this.author = author;
        this.name = name;
        this.publishingHouse = publishingHouse;
        this.publishYear = publishYear;
        this.pagesCount = pagesCount;
        this.price = price;
        this.retailMargin = retailMargin;
        this.availability = availability;
        this.countInShop = countInShop;
        this.status = status;
        this.rating = rating;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRetailMargin() {
        return retailMargin;
    }

    public void setRetailMargin(Integer retailMargin) {
        this.retailMargin = retailMargin;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Integer getCountInShop() {
        return countInShop;
    }

    public void setCountInShop(Integer countInShop) {
        this.countInShop = countInShop;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
