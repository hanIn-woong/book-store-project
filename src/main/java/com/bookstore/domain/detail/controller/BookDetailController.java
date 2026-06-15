package com.bookstore.domain.detail.controller;

import com.bookstore.common.database.BookDatabase;
import com.bookstore.common.exception.BookNotFoundException;
import com.bookstore.common.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookDetailController {

    private final BookDatabase bookDatabase = BookDatabase.getInstance();

    @GetMapping("/books/{bookId}")
    public String detail(@PathVariable String bookId, Model model) {
        Book book = bookDatabase.getBooks().stream()
                .filter(item -> item.getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(bookId));

        model.addAttribute("book", book);

        return "detail/detail";
    }
}