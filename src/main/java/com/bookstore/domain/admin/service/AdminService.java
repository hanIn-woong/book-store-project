package com.bookstore.domain.admin.service;

import com.bookstore.common.database.BookDatabase;
import com.bookstore.common.dto.BookDto;
import com.bookstore.common.exception.BookNotFoundException;
import com.bookstore.common.model.Book;
import com.bookstore.common.model.CartItem;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    /**
     * 장바구니 추가 로직 (재고 검증 포함)
     */
    public void addToCart(String userId, String bookId, int quantity) {
        Book book = database.getBooks().stream()
                .filter(b -> b.getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(bookId));

        // 1. 재고 확인
        if (book.getUnitsInStock() < quantity) {
            throw new RuntimeException("재고가 부족하여 장바구니에 담을 수 없습니다.");
        }

        // 2. 이미 장바구니에 있는지 확인
        Optional<CartItem> existingItem = database.getCartList().stream()
                .filter(item -> item.getUserId().equals(userId) && item.getBookId().equals(bookId))
                .findFirst();

        if (existingItem.isPresent()) {
            // 이미 있으면 수량 추가 (전체 수량이 재고를 넘지 않는지 확인)
            int totalQuantity = existingItem.get().getQuantity() + quantity;
            if (book.getUnitsInStock() < totalQuantity) {
                throw new RuntimeException("장바구니 총 수량이 재고를 초과합니다.");
            }
            existingItem.get().setQuantity(totalQuantity);
        } else {
            // 없으면 새로 추가
            CartItem newItem = CartItem.builder()
                    .userId(userId)
                    .bookId(bookId)
                    .bookName(book.getName())
                    .unitPrice(book.getUnitPrice())
                    .quantity(quantity)
                    .imageUrl(book.getImageUrl())
                    .addedDate(LocalDateTime.now())
                    .build();
            database.addCartItem(newItem);
        }
    }

    /**
     * 장바구니 수량 변경 로직 (재고 검증 포함)
     */
    public void updateCartItemQuantity(String cartId, int newQuantity) {
        CartItem item = database.getCartList().stream()
                .filter(i -> i.getCartId().equals(cartId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("장바구니 항목을 찾을 수 없습니다."));

        Book book = database.getBooks().stream()
                .filter(b -> b.getBookId().equals(item.getBookId()))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(item.getBookId()));

        if (book.getUnitsInStock() < newQuantity) {
            throw new RuntimeException("변경하려는 수량이 재고를 초과합니다.");
        }

        item.setQuantity(newQuantity);
    }

    /**
     * 재고 상태만 단순히 확인하는 유틸리티 메서드
     */
    public boolean isStockAvailable(String bookId, int requiredQuantity) {
        return database.getBooks().stream()
                .filter(b -> b.getBookId().equals(bookId))
                .findFirst()
                .map(book -> book.getUnitsInStock() >= requiredQuantity)
                .orElse(false);
    }
}
