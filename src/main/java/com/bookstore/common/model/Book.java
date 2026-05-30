package com.bookstore.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private String category;
    private int price;
    private String description;
    private String imageUrl;
}