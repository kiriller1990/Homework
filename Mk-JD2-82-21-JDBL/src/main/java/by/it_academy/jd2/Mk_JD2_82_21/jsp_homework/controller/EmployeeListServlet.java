package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.controller;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.CheckService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.EmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.api.IEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Search;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeListServlet ", urlPatterns = "/employeeList")
public class EmployeeListServlet extends HttpServlet {
    private final IEmployeeService iEmployeeService;

    private static final String PAGE_NUMBER_PARAM = "pageNumber";
    private static final long EMPLOYEES_IN_ONE_PAGE_PARAM = 20;

    public EmployeeListServlet() {
        this.iEmployeeService = EmployeeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        String pageNumber = req.getParameter(PAGE_NUMBER_PARAM);
        //добавил пагинацию
        long page;
        long lastPage = iEmployeeService.getTheNumberOfPages(EMPLOYEES_IN_ONE_PAGE_PARAM) ;
        if(pageNumber == null) {
            page = 1;
        }else {
            page = Long.parseLong(pageNumber);
        }
            List<Employee> employees = iEmployeeService.getEmployees(page, EMPLOYEES_IN_ONE_PAGE_PARAM);
            req.setAttribute("page",page);
            req.setAttribute("lastPage", lastPage);
            req.setAttribute("employees", employees);
            req.getRequestDispatcher("/views/employeeList.jsp").forward(req, resp);
    }
}
