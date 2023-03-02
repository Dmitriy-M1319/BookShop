package ru.vsu.cs.tech.bookshop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book_categories")
public class BookCategory  implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long categoryId;
    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Book> books;
    public BookCategory(String name) {
        this.name = name;
    }
}
