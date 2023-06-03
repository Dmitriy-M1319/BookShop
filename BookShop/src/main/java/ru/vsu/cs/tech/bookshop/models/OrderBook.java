package ru.vsu.cs.tech.bookshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "order_books")
public class OrderBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
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
}
