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

public class StateManager {

    private static final String key = "JSESSIONID";

    public static String updateSessionCookie(HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie = readCookie(request);

        cookie.setMaxAge(60);

        response.addCookie(cookie);

        return cookie.getValue();
    }

    public static void updateSession(HttpServletRequest request, User user, String sessionId) {

        HttpSession session = request.getSession(false);

        Instant minuteFromNow = Instant.now().plus(Duration.ofMinutes(1));

        user.cookieExpiry = Date.from(minuteFromNow);

        session.setAttribute(sessionId, user);
    }

    public static Cookie readCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if(cookies != null) {
            for (Cookie c : cookies) {
                if(c.getName() == key) {
                    return c;
                }
            }
        }

        return null;
    }

    public static User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        return (User)session.getAttribute(key);
    }

    public static void deleteUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute(key);

        session = request.getSession(true);
        session.setAttribute(key, null);
    }
}
