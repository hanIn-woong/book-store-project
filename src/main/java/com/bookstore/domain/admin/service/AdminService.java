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
        database.updateBook(updatedBook);
    }

    public void deleteBook(String bookId) {
        database.deleteBook(bookId);
    }
}
