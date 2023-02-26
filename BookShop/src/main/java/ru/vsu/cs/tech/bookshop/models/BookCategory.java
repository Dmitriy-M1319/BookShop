package ru.vsu.cs.tech.bookshop.models;

import java.io.Serializable;

public class BookCategory  implements Serializable {

    private Long categoryId;
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
