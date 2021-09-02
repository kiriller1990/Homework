package by.it_academy.jd2.Mk_JD2_82_21_output_homework.controller.Servletes;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.UserFactory;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api.EStorageType;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Users> userMap = UserFactory.getInstance().getUserMap();
        req.setAttribute("userMap", userMap);
        req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
    }
}
