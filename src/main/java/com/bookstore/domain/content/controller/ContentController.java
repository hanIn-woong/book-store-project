package com.bookstore.domain.content.controller;

import com.bookstore.common.database.BookDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContentController {

    private final BookDatabase database = BookDatabase.getInstance();

    @GetMapping("/books")
    public String bookList(@RequestParam(defaultValue = "grid") String view, Model model) {
        String selectedView = "list".equals(view) ? "list" : "grid";

        model.addAttribute("books", database.getBooks());
        model.addAttribute("view", selectedView);
        return "content/books";
    }
}
