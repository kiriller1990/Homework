package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.controller;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.CheckService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.EmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Search;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeFilterCriteriaServlet", urlPatterns = "/employeeListWithFilter")
public class EmployeeFilterCriteriaServlet extends HttpServlet {
    private static final String PAGE_NUMBER_PARAM = "pageNumber";
    private static final long EMPLOYEES_IN_ONE_PAGE_PARAM = 20;
    private static final String NAME_SEARCH_PARAMETER = "nameFilter";
    private static final String SALARY_LEVEL_PARAMETER = "salaryValue";
    private static final String TYPE_OF_SALARY_FILTER = "filterType";
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        String pageNumber = req.getParameter(PAGE_NUMBER_PARAM);
        String searchName = req.getParameter(NAME_SEARCH_PARAMETER);
        String salaryValueFilteredParam = req.getParameter(SALARY_LEVEL_PARAMETER);
        String typeOfSalaryFilter = req.getParameter(TYPE_OF_SALARY_FILTER);
        //добавил пагинацию
        long page;
        long lastPage;

        if (pageNumber == null) {
            page = 1;
        } else {
            page = Long.parseLong(pageNumber);
        }
        if (CheckService.getInstance().isFilterExist(searchName, salaryValueFilteredParam)) {
            double SalaryFilterParam = Double.parseDouble(salaryValueFilteredParam);
            if (searchName == null) {
                searchName = "";
            }
            Search search = new Search(searchName, SalaryFilterParam, typeOfSalaryFilter);
            List<Employee> employees = EmployeeService.getInstance().getEmployeeFilteredByNameOrSalary(search);
            lastPage = EmployeeService.getInstance().gerTheNumberOfPagesAfterFilteredByEmployeeOrSalary(employees, EMPLOYEES_IN_ONE_PAGE_PARAM);
            req.setAttribute("page", page);
            req.setAttribute("lastPage", lastPage);
            req.setAttribute("employees", employees);
            req.getRequestDispatcher("/views/employeeListWithFilter.jsp").forward(req, resp);
        }
    }
}
