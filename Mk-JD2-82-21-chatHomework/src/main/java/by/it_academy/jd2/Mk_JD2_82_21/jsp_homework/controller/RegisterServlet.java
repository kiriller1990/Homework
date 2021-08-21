package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.controller;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.LoginService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.SessionService;
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

        PrintWriter writer = resp.getWriter();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        HttpSession session = req.getSession();

        /*if(session.isNew()) {*/
            if (LoginService.getInstance().isExist(login, password)) {
                req.getRequestDispatcher("/views/SignUp_allreadyExist_exception.jsp").forward(req, resp);
            } else {
                Users user = new Users(login, password, name, date);
                UserStorageService.getInstance().setUser(login, user);
                SessionService.getInstance().save(req, user);
                req.getRequestDispatcher("/views/SignIn.jsp").forward(req, resp);
                //проверка что пользователь создан удалить перед залитием
               /* writer.println("<p>Login: " + UserStorageService.getInstance().getUser(login).getLogin() + "</p>");
                writer.println("<p>Password: " + UserStorageService.getInstance().getUser(login).getPassword() + "</p>");
                writer.println("<p>Name: " + UserStorageService.getInstance().getUser(login).getName() + "</p>");
                writer.println("<p>Date: " + UserStorageService.getInstance().getUser(login).getDate() + "</p>");*/
            }
       /* } else {*/

       /* }*/

        /*Users checkingUser = UserStorageService.getInstance().getUser(login);
        if (checkingUser.getLogin().equals(login) ) {
            servletRequest.getRequestDispatcher("/views/AlreadyExist_exception.jsp").forward(servletRequest, servletResponse);
        }*/

       /* servletRequest.getRequestDispatcher("/views/message.jsp").forward(servletRequest, servletResponse);*/


            /*writer.println("<p>Login: " + UserStorageService.getInstance().getUser(login).getLogin() + "</p>");//проверяем вычитали ли параметры
            writer.println("<p>Password: " + UserStorageService.getInstance().getUser(login).getPassword() + "</p>");
            writer.println("<p>Name: " + UserStorageService.getInstance().getUser(login).getName() + "</p>");
            writer.println("<p>Date: " + UserStorageService.getInstance().getUser(login).getDate() + "</p>");*/

    }
}
