package ru.vsu.cs.tech.bookshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BookCategory category;

    @OneToMany
    @JoinColumn(name = "id")
    List<BooksQuery> queries;
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


}
