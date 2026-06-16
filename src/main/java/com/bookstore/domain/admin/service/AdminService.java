package com.bookstore.domain.admin.service;

import com.bookstore.common.database.BookDatabase;
import com.bookstore.common.dto.BookDto;
import com.bookstore.common.exception.BookNotFoundException;
import com.bookstore.common.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final BookDatabase database = BookDatabase.getInstance();

    public List<BookDto> getAllBooks() {
        return database.getBooks().stream()
                .map(BookDto::from)
                .toList();
    }

    public BookDto getBookById(String bookId) {
        Book book = database.getBooks().stream()
                .filter(b -> b.getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(bookId));
        return BookDto.from(book);
    }

    public void addBook(BookDto bookDto) {
        database.addBook(bookDto.toEntity());
    }

    public void updateBook(BookDto updatedBookDto) {
        Book updatedBook = updatedBookDto.toEntity();
        Book existingBook = database.getBooks().stream()
                .filter(b -> b.getBookId().equals(updatedBook.getBookId()))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(updatedBook.getBookId()));
        
        // 기존 통계 데이터 보존
        updatedBook.setSalesCount(existingBook.getSalesCount());
        updatedBook.setViewCount(existingBook.getViewCount());
        database.updateBook(updatedBook);
    }

    public void deleteBook(String bookId) {
        database.deleteBook(bookId);
    }

    public long getTotalStock() {
        return database.getBooks().stream().mapToLong(Book::getUnitsInStock).sum();
    }

    public List<BookDto> getLowStockBooks() {
        return database.getBooks().stream()
                .filter(book -> book.getUnitsInStock() <= 3)
                .map(BookDto::from)
                .toList();
    }

    public long getOutOfStockCount() {
        return database.getBooks().stream()
                .filter(book -> book.getUnitsInStock() == 0)
                .count();
    }

    public List<BookDto> getBestSellers() {
        return database.getBooks().stream()
                .sorted((b1, b2) -> Long.compare(b2.getSalesCount(), b1.getSalesCount()))
                .limit(5)
                .map(BookDto::from)
                .toList();
    }

    public java.util.Map<String, Long> getCategoryDistribution() {
        return database.getBooks().stream()
                .collect(java.util.stream.Collectors.groupingBy(Book::getCategory, java.util.stream.Collectors.counting()));
    }

    /**
     * 구매 처리 프로세스: 재고 차감, 판매량 증가, 구매 이력 저장
     */
    public void processPurchase(String userId, String bookId, int quantity) {
        Book book = database.getBooks().stream()
                .filter(b -> b.getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(bookId));
        
        // 1. 재고 차감
        if (book.getUnitsInStock() < quantity) {
            throw new RuntimeException("재고가 부족합니다.");
        }
        book.setUnitsInStock(book.getUnitsInStock() - quantity);
        
        // 2. 판매량 증가
        book.setSalesCount(book.getSalesCount() + quantity);
        
        // 3. 구매 이력 저장
        com.bookstore.common.model.Purchase purchase = com.bookstore.common.model.Purchase.builder()
                .userId(userId)
                .bookId(bookId)
                .bookName(book.getName())
                .unitPrice(book.getUnitPrice())
                .purchaseDate(java.time.LocalDateTime.now())
                .build();
        
        database.addPurchase(purchase);
    }
}
