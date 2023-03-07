package ru.vsu.cs.tech.bookshop.dto;

import lombok.Data;

@Data
public class OrderBookPostDto {
    private Long orderId;
    private String bookName;
    private String bookAuthor;
    private String bookTag;
    private Integer booksCount;
}
