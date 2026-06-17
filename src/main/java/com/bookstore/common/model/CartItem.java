package com.bookstore.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    private String cartId;
    private String userId;
    private String bookId;
    private String bookName;
    private int unitPrice;
    private int quantity;
    private String imageUrl;
    private LocalDateTime addedDate;
}
