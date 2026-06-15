package com.bookstore.common.exception;

public class BookNotFoundException extends RuntimeException {
    private final String bookId;

    public BookNotFoundException(String bookId) {
        super("도서를 찾을 수 없습니다. (ID: " + bookId + ")");
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
