package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.controller;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.CheckService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.EmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.api.IEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/")
public class RegisterServlet extends HttpServlet {
    private IEmployeeService iEmployeeService;
    private static final String NAME_PARAM = "name";
    private static final String SALARY_PARAM = "salary";
    private ObjectMapper mapper = new ObjectMapper();

    public RegisterServlet() {
        this.iEmployeeService = EmployeeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html; charset=UTF-8");
        req.getRequestDispatcher("/views/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String name = req.getParameter(NAME_PARAM);
        String salaryParam = req.getParameter(SALARY_PARAM);

        try {
            CheckService.getInstance().registerCheck(name,salaryParam);
            double salary = Double.parseDouble(salaryParam);
            Employee employee = new Employee();
                employee.setName(name);
                employee.setSalary(salary);
           // Employee employee = mapper.readValue(req.getInputStream(),Employee.class);
            long id = iEmployeeService.addEmployee(employee);
            req.setAttribute("id", id);
            req.getRequestDispatcher("/views/id.jsp").forward(req, resp);
        }catch (IllegalArgumentException e) {
            req.setAttribute("name", e.getMessage());
            getServletContext().getRequestDispatcher("/views/start.jsp").forward(req,resp);
        }
    }
}
