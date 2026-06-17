package com.bookstore.domain.content.controller;

import com.bookstore.domain.content.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/books")
    public String bookList(
            @RequestParam(defaultValue = "grid")    String view,
            @RequestParam(defaultValue = "")        String keyword,
            @RequestParam(defaultValue = "")        String category,
            @RequestParam(defaultValue = "default") String sort,
            Model model) {

        model.addAttribute("books",      contentService.search(keyword, category, sort));
        model.addAttribute("categories", contentService.getCategories());
        model.addAttribute("todayBook",  contentService.getTodayBook());
        model.addAttribute("view",       "list".equals(view) ? "list" : "grid");
        model.addAttribute("keyword",    keyword);
        model.addAttribute("category",   category);
        model.addAttribute("sort",       sort);

        return "content/books";
    }
}
