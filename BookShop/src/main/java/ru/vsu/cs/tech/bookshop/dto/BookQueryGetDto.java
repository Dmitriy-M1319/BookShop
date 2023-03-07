package ru.vsu.cs.tech.bookshop.dto;

import lombok.Data;

@Data
public class BookQueryGetDto {
    private Long id;
    private String publishingHouse;
    private BookGetDto book;
    private Integer booksCount;
    private String status;
}
