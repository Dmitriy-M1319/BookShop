package ru.vsu.cs.tech.bookshop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "queries")
public class BooksQuery implements Serializable {
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

    public BooksQuery(String publishingHouse, Book book, Integer booksCount, String status) {
        this.publishingHouse = publishingHouse;
        this.book = book;
        this.booksCount = booksCount;
        this.status = status;
    }
}
