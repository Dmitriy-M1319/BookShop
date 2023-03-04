package ru.vsu.cs.tech.bookshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue
    private Long bookId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BookCategory category;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    List<BooksQuery> queries;
    @Column(name = "author")
    private String author;
    @Column(name = "name")
    private String name;
    @Column(name = "publishing_house")
    private String publishingHouse;
    @Column(name = "publish_year")
    private Integer publishYear;
    @Column(name = "pages_count")
    private Integer pagesCount;
    @Column(name = "price")
    private Integer price;
    @Column(name = "retail_margin")
    private Integer retailMargin;
    @Column(name = "availability")
    private Boolean availability;
    @Column(name = "count_in_shop")
    private Integer countInShop;
    @Column(name = "status")
    private String status;
    @Column(name = "rating")
    private Float rating;

    public Book(String author,
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
}
