package ru.vsu.cs.tech.bookshop.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
public class Book implements Serializable{

    @Id
    @GeneratedValue
    private Long bookId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BookCategory category;
    @Column
    private String author;
    @Column
    private String name;
    @Column(name = "publishing_house")
    private String publishingHouse;
    @Column(name = "publish_year")
    private Integer publishYear;
    @Column(name = "pages_count")
    private Integer pagesCount;
    @Column
    private Integer price;
    @Column(name = "retail_margin")
    private Integer retailMargin;
    @Column
    private Boolean availability;
    @Column(name = "count_in_shop")
    private Integer countInShop;
    @Column
    private String status;
    @Column
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

    public Book() {
    }

    public void copyInfoFromAnotherBook(Book newBook) {
        this.category = newBook.category;
        this.author = newBook.author;
        this.name = newBook.name;
        this.publishingHouse = newBook.publishingHouse;
        this.publishYear = newBook.publishYear;
        this.pagesCount = newBook.pagesCount;
        this.price = newBook.price;
        this.retailMargin = newBook.retailMargin;
        this.availability = newBook.availability;
        this.countInShop = newBook.countInShop;
        this.status = newBook.status;
        this.rating = newBook.rating;
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
