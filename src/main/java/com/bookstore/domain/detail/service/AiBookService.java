package com.bookstore.domain.detail.service;

import com.bookstore.common.model.Book;
import org.springframework.stereotype.Service;

@Service
public class AiBookService {

    public AiBookSummary getSummary(Book book) {
        String oneLinePitch = "\"" + book.getName() + "\" — " + book.getCategory() + " 장르의 필독서";
        String detailedSummary = book.getDescription() + " " + book.getAuthor() + "의 작품으로, "
                + book.getPublisher() + "에서 출간되었습니다.";
        String targetReader = book.getCategory() + " 장르를 즐기는 독자, " + book.getAuthor() + "의 팬";
        String purchasePoints = "출간일: " + book.getReleaseDate()
                + " | 현재 재고: " + book.getUnitsInStock() + "권"
                + " | 누적 판매: " + book.getSalesCount() + "권";

        return new AiBookSummary(oneLinePitch, detailedSummary, targetReader, purchasePoints);
    }
}
