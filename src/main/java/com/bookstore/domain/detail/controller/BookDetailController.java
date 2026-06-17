package com.bookstore.domain.detail.controller;

import com.bookstore.common.database.BookDatabase;
import com.bookstore.common.exception.BookNotFoundException;
import com.bookstore.common.model.Book;
import com.bookstore.domain.detail.service.AiBookService;
import com.bookstore.domain.detail.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BookDetailController {

    private final BookDatabase bookDatabase = BookDatabase.getInstance();
    private final DetailService detailService;
    private final AiBookService aiBookService;

    @GetMapping("/books/{bookId}")
    public String detail(@PathVariable String bookId, Model model) {
        Book book = bookDatabase.getBooks().stream()
                .filter(item -> item.getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(bookId));

        book.setViewCount(book.getViewCount() + 1);

        model.addAttribute("book", book);
        model.addAttribute("relatedBooks", detailService.getRelatedBooks(bookId, 4));
        model.addAttribute("prevBook", detailService.getPrevBook(bookId));
        model.addAttribute("nextBook", detailService.getNextBook(bookId));

        try {
            model.addAttribute("aiSummary", aiBookService.getSummary(book));
        } catch (Exception ignored) {
            // LLM API 장애 시에도 페이지 정상 동작 보장
        }

        return "detail/detail";
    }
}