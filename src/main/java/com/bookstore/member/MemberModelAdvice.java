package com.bookstore.member;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MemberModelAdvice {

    @ModelAttribute
    public void addLoginMember(Model model, HttpSession session) {
        model.addAttribute("loginUserId", session.getAttribute("loginUserId"));
        model.addAttribute("loginRole", session.getAttribute("loginRole"));
    }
}
