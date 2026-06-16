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
public class Purchase {
    private String purchaseId;    // 구매 고유 ID
    private String userId;        // 회원 ID
    private String bookId;        // 도서 ID
    private String bookName;      // 도서명 (구매 당시 이름)
    private int unitPrice;        // 가격 (구매 당시 가격)
    private LocalDateTime purchaseDate; // 구매 일시
}
