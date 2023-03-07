package ru.vsu.cs.tech.bookshop.dto;

import lombok.Data;

@Data
public class BookQueryPostDto {
    private String publishingHouse;
    private Long bookId;
    private Integer booksCount;
    private String status;
}
