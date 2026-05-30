package com.bookstore.domain.detail.controller;

import com.bookstore.common.database.BookRepository;
import com.bookstore.common.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BookDetailController {

    private final BookRepository bookRepository;

    @GetMapping("/books/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 도서입니다. id=" + id));

        model.addAttribute("book", book);

        return "detail/detail";
    }
}