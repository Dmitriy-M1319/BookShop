package ru.vsu.cs.tech.bookshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String customerSurname;
    private String phoneNumber;
    private String email;
    private String status;
}
