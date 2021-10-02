package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.controller;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.DepartmentService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.DBDepartmentStorage;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.EmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DepartmentServlet ", urlPatterns = "/department")
public class DepartmentServlet extends HttpServlet {
    private static final String ID_PARAM = "id";
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(ID_PARAM);
        if(id == null) {
            List<Department> departments = DepartmentService.getInstance().getDepartmentList();
            req.setAttribute("department", departments);
            req.getRequestDispatcher("/views/departmentList.jsp").forward(req, resp);
        } else {
            List<Employee> employees = EmployeeService.getInstance().getEmployeesSortedByDepartment(id);
            req.setAttribute("employees",employees);
            req.getRequestDispatcher("/views/employeeList.jsp").forward(req, resp);

        }
    }
}
