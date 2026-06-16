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
        // 임시 테스트를 위해 보안 체크 비활성화
        return true;
        /*
        HttpSession session = request.getSession(false);
        
        // 세션이 없거나, 로그인한 사용자의 ID가 'admin'이 아닌 경우 로그인 페이지로 리다이렉트
        if (session == null || !"admin".equals(session.getAttribute("loginUserId"))) {
            response.sendRedirect("/member/login");
            return false;
        }
        
        return true;
        */
    }
}
