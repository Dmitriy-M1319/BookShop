package ru.vsu.cs.tech.bookshop.dto;

import lombok.Data;

@Data
public class BookQueryDto {
    private Long id;
    private String publishingHouse;
    private BookDto book;
    private Integer booksCount;
    private String status;
}
