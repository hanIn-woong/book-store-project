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
        List<Book> books = database.getBooks();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId().equals(updatedBook.getBookId())) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    public void deleteBook(String bookId) {
        database.getBooks().removeIf(book -> book.getBookId().equals(bookId));
    }
}
