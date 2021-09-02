package by.it_academy.jd2.Mk_JD2_82_21_output_homework.controller.Servletes;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.LoginService;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.SessionService;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.UserFactory;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String login = req.getParameter(LOGIN_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);

        try {
            LoginService.getInstance().loginCheck(login,password);
            Users user = UserFactory.getInstance().getUser(login);
            SessionService.getInstance().save(req,user);
            req.getRequestDispatcher("/views/message.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("name", e.getMessage());
            getServletContext().getRequestDispatcher("/views/SignIn.jsp").forward(req,resp);
        }
    }
}
