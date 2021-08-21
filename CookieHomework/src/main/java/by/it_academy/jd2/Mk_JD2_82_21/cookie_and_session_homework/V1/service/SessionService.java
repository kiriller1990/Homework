package by.it_academy.jd2.Mk_JD2_82_21.cookie_and_session_homework.V1.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionService {
    public static String getValueForSession (HttpServletRequest req, String key) {
        String value = req.getParameter(key);

        if(value == null) {
            HttpSession session = req.getSession();

            if(!session.isNew()) {
                value = (String) session.getAttribute(key);
            }
        }
        if(value == null) {
            throw new IllegalArgumentException("Не передан один из обязательных параметров");
        }
        return value;
    }

    public static void saveSession(HttpServletRequest req, String key, String val) {
        HttpSession session = req.getSession();
        session.setAttribute(key,val);
    }

}
