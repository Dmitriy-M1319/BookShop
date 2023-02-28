package ru.vsu.cs.tech.bookshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class BookCategory  implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long categoryId;
    @Column
    private String name;

    public BookCategory(String name) {
        this.name = name;
    }

    public BookCategory() {

    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
