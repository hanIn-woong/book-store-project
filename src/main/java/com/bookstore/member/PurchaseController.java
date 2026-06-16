package com.bookstore.member;

import com.bookstore.common.dto.BookDto;
import com.bookstore.domain.admin.service.AdminService;
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
public class PurchaseController {

    private static final int PURCHASE_QUANTITY = 1;
    private final AdminService adminService;

    @PostMapping("/member/purchase/{bookId}/start")
    public String startPurchase(@PathVariable String bookId, HttpSession session) {
        String loginUserId = getLoginUserId(session);
        if (loginUserId == null) {
            return "redirect:/member/id-input";
        }

        BookDto book = adminService.getBookById(bookId);
        if (book.getUnitsInStock() < PURCHASE_QUANTITY) {
            return "redirect:/books/" + bookId + "?purchaseError=outOfStock";
        }

        return "redirect:/member/purchase/" + bookId + "/payment";
    }

    @GetMapping("/member/purchase/{bookId}/payment")
    public String paymentForm(@PathVariable String bookId, HttpSession session, Model model) {
        String loginUserId = getLoginUserId(session);
        if (loginUserId == null) {
            return "redirect:/member/id-input";
        }

        BookDto book = adminService.getBookById(bookId);
        if (book.getUnitsInStock() < PURCHASE_QUANTITY) {
            return "redirect:/books/" + bookId + "?purchaseError=outOfStock";
        }

        model.addAttribute("book", book);
        return "member/payment";
    }

    @PostMapping("/member/purchase/{bookId}/complete")
    public String completePurchase(
            @PathVariable String bookId,
            @RequestParam(required = false) String paymentMethod,
            HttpSession session,
            Model model
    ) {
        String loginUserId = getLoginUserId(session);
        if (loginUserId == null) {
            return "redirect:/member/id-input";
        }

        BookDto book = adminService.getBookById(bookId);
        if (!isValidPaymentMethod(paymentMethod)) {
            model.addAttribute("book", book);
            model.addAttribute("error", "결제 수단을 다시 선택해 주세요.");
            return "member/payment";
        }

        try {
            adminService.processPurchase(loginUserId, bookId, PURCHASE_QUANTITY);
        } catch (RuntimeException e) {
            return "redirect:/books/" + bookId + "?purchaseError=outOfStock";
        }

        return "redirect:/books/" + bookId + "?purchaseSuccess=true";
    }

    private String getLoginUserId(HttpSession session) {
        Object loginUserId = session.getAttribute("loginUserId");
        return loginUserId == null ? null : loginUserId.toString();
    }

    private boolean isValidPaymentMethod(String paymentMethod) {
        return "CARD".equals(paymentMethod) || "BANK".equals(paymentMethod);
    }
}
