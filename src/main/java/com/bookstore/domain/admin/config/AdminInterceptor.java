package com.bookstore.domain.admin.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        // 1. 세션이 없거나 로그인 정보가 없는 경우 로그인 페이지로 리다이렉트
        if (session == null || session.getAttribute("loginUserId") == null) {
            response.sendRedirect("/member/login");
            return false;
        }

        // 2. 관리자 권한(ADMIN)이 없는 경우 도서 목록(/books)으로 리다이렉트
        String role = (String) session.getAttribute("loginRole");
        if (!"ADMIN".equals(role)) {
            response.sendRedirect("/books");
            return false;
        }

        return true;
    }
}
