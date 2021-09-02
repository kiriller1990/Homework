package by.it_academy.jd2.Mk_JD2_82_21_output_homework.controller.Servletes;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.*;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", urlPatterns = "/")
public class RegisterServlet extends HttpServlet {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String NAME_PARAM = "name";
    private static final String DATE_PARAM = "date";

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        req.getRequestDispatcher("/views/SignUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String login = req.getParameter(LOGIN_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);
        String name = req.getParameter(NAME_PARAM);
        String date = req.getParameter(DATE_PARAM);


        try {
            LoginService.getInstance().registerCheck(login, password,name,date);
            Users user = new Users(login, password, name, date);
            UserFactory.getInstance().setUser(login,user);
            req.getRequestDispatcher("/views/SignIn.jsp").forward(req, resp);
        }catch (IllegalArgumentException e) {
            req.setAttribute("name", e.getMessage());
            getServletContext().getRequestDispatcher("/views/SignUp.jsp").forward(req,resp);
        }
    }
}
