package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.controller;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.CheckService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.DBInitializer;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "CardEmployeServlet ", urlPatterns = "/cardEmployee")
    public class CardEmployeServlet extends HttpServlet {
        private static final String ID_PARAM_NAME= "id_card";



        @Override
        protected void doGet(HttpServletRequest req,
                             HttpServletResponse resp) throws ServletException, IOException
        {
            String idParam = req.getParameter(ID_PARAM_NAME);
            try {
                CheckService.getInstance().idCheck(idParam);
                long id = Long.parseLong(idParam);
                Employee employee = DBInitializer.getInstance().getEmployee(id);
                req.setAttribute("employee",employee);

                req.getRequestDispatcher("/views/cardView.jsp").forward(req, resp);

            }catch (IllegalArgumentException e) {
                req.setAttribute("name", e.getMessage());
                getServletContext().getRequestDispatcher("/views/card.jsp").forward(req, resp);
            }
        }


    }

