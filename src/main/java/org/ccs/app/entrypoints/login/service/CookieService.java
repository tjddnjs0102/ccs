package org.ccs.app.entrypoints.login.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    @Value("${jwt.expiration-ms.access}")
    private int jwtAccessExpirationMs;

    @Value("${jwt.expiration-ms.refresh}")
    private int jwtRefreshExpirationMs;

    public void addCookie(HttpServletResponse response, String name, String value, boolean isAccessToken) {
        int maxAge = isAccessToken ? jwtAccessExpirationMs : jwtRefreshExpirationMs;
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
}
