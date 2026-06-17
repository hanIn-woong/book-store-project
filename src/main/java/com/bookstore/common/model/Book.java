package com.bookstore.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private String bookId;
    private String name;
    private int unitPrice;
    private String author;
    private String description;
    private String publisher;
    private String category;
    private long unitsInStock;
    private String releaseDate;
    private String condition; // New, Old, Refurbished
    private String imageUrl;
    
    @Builder.Default
    private long salesCount = 0; // 누적 판매량
    
    @Builder.Default
    private long viewCount = 0;  // 조회수
}
