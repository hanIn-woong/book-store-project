package com.bookstore.domain.detail.controller;

import com.bookstore.common.database.BookDatabase;
import com.bookstore.common.exception.BookNotFoundException;
import com.bookstore.common.model.Book;
import com.bookstore.common.util.SessionUtils;
import com.bookstore.domain.detail.service.AiBookService;
import com.bookstore.domain.detail.service.DetailService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookDetailController {

    private final BookDatabase bookDatabase;
    private final DetailService detailService;
    private final AiBookService aiBookService;

    @GetMapping("/books/{bookId}")
    public String detail(@PathVariable String bookId, Model model, HttpSession session) {
        Book book = bookDatabase.getBooks().stream()
                .filter(item -> item.getBookId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(bookId));

        book.setViewCount(book.getViewCount() + 1);

        String loginUserId = SessionUtils.getLoginUserId(session);

        model.addAttribute("book", book);
        model.addAttribute("relatedBooks", detailService.getRelatedBooks(bookId, 4));
        model.addAttribute("prevBook", detailService.getPrevBook(bookId));
        model.addAttribute("nextBook", detailService.getNextBook(bookId));
        model.addAttribute("avgRating", detailService.getAverageRating(bookId));
        model.addAttribute("ratingCount", detailService.getRatingCount(bookId));
        model.addAttribute("reviews", detailService.getReviews(bookId));
        model.addAttribute("userRating", loginUserId != null ? detailService.getUserRating(loginUserId, bookId) : 0);

        try {
            model.addAttribute("aiSummary", aiBookService.getSummary(book));
        } catch (Exception ignored) {
            // LLM API 장애 시에도 페이지 정상 동작 보장
        }

        return "detail/detail";
    }

    @PostMapping("/books/{bookId}/rate")
    public String rateBook(
            @PathVariable String bookId,
            @RequestParam int score,
            @RequestParam(required = false, defaultValue = "") String comment,
            HttpSession session
    ) {
        String loginUserId = SessionUtils.getLoginUserId(session);
        if (loginUserId == null) {
            return "redirect:/member/id-input";
        }

        detailService.rateBook(loginUserId, bookId, score, comment);
        return "redirect:/books/" + bookId;
    }
}
