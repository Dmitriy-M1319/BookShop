package ru.vsu.cs.tech.bookshop.dto;

import lombok.Data;

@Data
public class OrderBookDto {
    private Long id;
    private OrderDto order;
    private String bookName;
    private String bookAuthor;
    private String bookTag;
    private Integer booksCount;
}
