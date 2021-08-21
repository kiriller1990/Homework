package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.controller;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.LoginService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.UserStorageService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";

   /* @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        req.getRequestDispatcher("/views/SignIn.jsp").forward(req, resp);
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        String login = req.getParameter(LOGIN_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);
            if (LoginService.getInstance().isLoginCorrect(login,password)) {
                Users user = UserStorageService.getInstance().getUser(login);
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
            req.getRequestDispatcher("/views/message.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/views/SignIn_login_exception.jsp").forward(req, resp);
            }
    }
}
