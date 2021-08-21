package by.it_academy.jd2.Mk_JD2_82_21.cookie_and_session_homework.V1.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CookieService {

    public static String getValueForCookie(HttpServletRequest req, String key) {
        String value = req.getParameter(key);

        if(value == null) {
            Cookie[] cookies = req.getCookies();
            if(cookies != null) {
                value = Arrays.stream(cookies)
                        .filter(c->key.equalsIgnoreCase(c.getName()))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null);
            }
        }
        if(value == null || value == "") {
            throw new IllegalArgumentException("не передан один из обязательных параметров");
        }
        return value;
    }

    public static void sendCookie(HttpServletResponse resp, String key, String value) {
        Cookie cookie = new Cookie(key, URLEncoder.encode(value, StandardCharsets.UTF_8));
        resp.addCookie(cookie);
    }
}
