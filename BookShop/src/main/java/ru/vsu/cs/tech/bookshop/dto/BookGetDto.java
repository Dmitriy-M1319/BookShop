package ru.vsu.cs.tech.bookshop.dto;

import lombok.Data;

@Data
public class BookGetDto {
    private Long id;
    private BookCategoryDto category;
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
