package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.DBService;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.DataSource;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Position;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBEmployeeService {
    private static DBEmployeeService instance = new DBEmployeeService();

    private DBEmployeeService(){
    }

    public static DBEmployeeService getInstance() {
        return instance;
    }

    public Position getPosition (long positionId) {
        Position position = null;
        String sql = "SELECT id, position FROM application.positions" +
                "WHERE id = " + positionId;
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
        ){
            try(ResultSet resultSet = statement.executeQuery(sql)){

                while (resultSet.next()){
                    String positions = resultSet.getString(2);
                    position = new Position(positionId, positions);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return position;
    }

    public List<Employee> getEmployeeList(){
        List<Employee> listOfEmployees = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT id, name,salary \n"+
                    "FROM application.employes")){
                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);
                    Employee employee = new Employee(id, name, salary);
                    listOfEmployees.add(employee);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return listOfEmployees;
    }

    public List<Employee> getEmployeeList(String parametr){
        List<Employee> listOfEmployees = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery(parametr)){
                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);
                    String title = resultSet.getString(4);
                    String position = resultSet.getString(5);

                    Employee employee = new Employee(id, name, salary,title,position);
                    listOfEmployees.add(employee);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return listOfEmployees;
    }
}

