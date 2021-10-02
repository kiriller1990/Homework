package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.controller;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.PositionService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.DBPositionStorage;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.EmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Position;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PositionServlet ", urlPatterns = "/position")
public class PositionServlet extends HttpServlet {
    private static final String ID_PARAM = "id";
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(ID_PARAM);
        if(id == null) {
        List<Position> position = PositionService.getInstance().getPositionList();
        req.setAttribute("position",position);
        req.getRequestDispatcher("/views/positionList.jsp").forward(req, resp);
        } else {
            List<Employee> employees = EmployeeService.getInstance().getEmployeesSortedByPosition(id);
            req.setAttribute("employees",employees);
            req.getRequestDispatcher("/views/employeeList.jsp").forward(req, resp);

        }
    }
}
