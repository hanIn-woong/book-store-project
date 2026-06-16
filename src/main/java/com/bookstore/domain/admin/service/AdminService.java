package com.bookstore.domain.admin.service;

import com.bookstore.common.database.BookDatabase;
import com.bookstore.common.exception.BookNotFoundException;
import com.bookstore.common.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final BookDatabase database = BookDatabase.getInstance();

    public List<Book> getAllBooks() {
        return database.getBooks();
    }

    public Book getBookById(String bookId) {
        return database.getBooks().stream()
                .filter(book -> book.getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    public void addBook(Book book) {
        database.addBook(book);
    }

    public void updateBook(Book updatedBook) {
        Book existingBook = getBookById(updatedBook.getBookId());
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

    public List<Book> getLowStockBooks() {
        return database.getBooks().stream()
                .filter(book -> book.getUnitsInStock() <= 3)
                .toList();
    }

    public List<Book> getBestSellers() {
        return database.getBooks().stream()
                .sorted((b1, b2) -> Long.compare(b2.getSalesCount(), b1.getSalesCount()))
                .limit(5)
                .toList();
    }

    public java.util.Map<String, Long> getCategoryDistribution() {
        return database.getBooks().stream()
                .collect(java.util.stream.Collectors.groupingBy(Book::getCategory, java.util.stream.Collectors.counting()));
    }
}
