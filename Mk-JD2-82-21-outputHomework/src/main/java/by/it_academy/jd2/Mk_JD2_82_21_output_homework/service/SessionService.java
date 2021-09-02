package by.it_academy.jd2.Mk_JD2_82_21_output_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionService {
        private final static SessionService instance = new SessionService();
        private final static String USERS_ATTRIBUTE_NAME = "user";

        private SessionService() {
        }

        public void save(HttpServletRequest req, Users user) {
            HttpSession session = req.getSession();
            session.setAttribute(USERS_ATTRIBUTE_NAME, user);
        }







        public static SessionService getInstance() {
            return instance;
        }
}
