package com.rigatron.rigs4j.web.Utilities;

import com.rigatron.rigs4j.web.models.User;
import org.hibernate.Session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class StateManager {

    private static final String key = "TOKEN";

    public static String updateSessionCookie(HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie = getCookie(request);

        if(cookie == null) {
            cookie = new Cookie(key, UUID.randomUUID().toString());
        }

        cookie.setMaxAge(120);

        response.addCookie(cookie);

        return cookie.getValue();
    }

    public static void updateSession(HttpServletRequest request, User user, String sessionId) {

        HttpSession session = request.getSession(true);

        Instant minuteFromNow = Instant.now().plus(Duration.ofMinutes(2));

        user.cookieExpiry = Date.from(minuteFromNow);

        session.setAttribute(sessionId, user);
    }

    public static Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if(cookies != null) {
            for (Cookie c : cookies) {
                if(c.getName().equals(key)) {
                    return c;
                }
            }
        }

        return null;
    }

    public static User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        Cookie cookie = getCookie(request);

        return (User)session.getAttribute(cookie.getValue());
    }

    public static void deleteUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute(key);

        session = request.getSession(true);
        session.setAttribute(key, null);
    }
}
