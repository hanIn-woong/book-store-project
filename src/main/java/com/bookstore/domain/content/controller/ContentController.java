package com.bookstore.domain.content.controller;

import com.bookstore.common.database.BookDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    private final BookDatabase database = BookDatabase.getInstance();

    @GetMapping("/books")
    public String bookList(Model model) {
        model.addAttribute("books", database.getBooks());
        return "content/books";
    }
}
