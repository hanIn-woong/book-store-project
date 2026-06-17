package com.bookstore.common.util;

import jakarta.servlet.http.HttpSession;

public class SessionUtils {

    private SessionUtils() {}

    public static String getLoginUserId(HttpSession session) {
        Object loginUserId = session.getAttribute("loginUserId");
        return loginUserId == null ? null : loginUserId.toString();
    }
}
