package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.DBService;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*public class DBPoginationService {

    public List<Employee> getEmployee(String name, double salary, long department, long position, ComboPooledDataSource cpds) {
        List<Employee> listOfEmployees = new ArrayList<>();
        try (Connection con = cpds.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement("SELECT id, name, salary FROM application.employes ORDER BY id ASC LIMIT ? OFFSET ?")
            ) {
                preparedStatement.setLong(1,10);
                preparedStatement.setLong(2,20);
            }

        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
    }
}*/   //оптимистическая и писсиместическая блокировка
