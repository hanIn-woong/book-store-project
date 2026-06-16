package com.bookstore.domain.admin.controller;

import com.bookstore.common.model.Book;
import com.bookstore.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping({"", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("totalBooks", adminService.getAllBooks().size());
        model.addAttribute("totalStock", adminService.getTotalStock());
        model.addAttribute("lowStockBooks", adminService.getLowStockBooks());
        model.addAttribute("bestSellers", adminService.getBestSellers());
        model.addAttribute("categoryStats", adminService.getCategoryDistribution());
        return "admin/dashboard";
    }

    @GetMapping("/books")
    public String bookList(Model model) {
        model.addAttribute("books", adminService.getAllBooks());
        return "admin/books";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        adminService.addBook(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/edit/{bookId}")
    public String editForm(@PathVariable String bookId, Model model) {
        model.addAttribute("book", adminService.getBookById(bookId));
        return "admin/updateBook";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book) {
        adminService.updateBook(book);
        return "redirect:/admin/books";
    }

    @PostMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        adminService.deleteBook(bookId);
        return "redirect:/admin/books";
    }
}
