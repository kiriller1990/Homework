package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.util.DataSource;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBEmployeeStorage {
    private static DBEmployeeStorage instance = new DBEmployeeStorage();

    private DBEmployeeStorage(){
    }

    public long addEmployee(String name, double salary) {
        long id;
        try (Connection con = DataSource.getConnection()) {

            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employes(\n" +
                    "name, salary)\n" +
                    "VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS)
            ) {
                preparedStatement.setString(1, name);
                preparedStatement.setDouble(2, salary);
                preparedStatement.executeUpdate();
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    generatedKeys.next();
                    id = generatedKeys.getLong(1);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return id;
    }

    public long addEmployee(String name, double salary, long department, long position) {
        long id;
        try (Connection con = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employes(\n" +
                    "name, salary, departments, position)\n" +
                    "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)
            ) {
                preparedStatement.setString(1, name);
                preparedStatement.setDouble(2, salary);
                preparedStatement.setLong(3, department);
                preparedStatement.setLong(4, position    );
                preparedStatement.executeUpdate();
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    generatedKeys.next();
                    id = generatedKeys.getLong(1);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return id;
    }

    public Employee getEmployeeCard(String idEmployee){
        Employee employee;
        String sgl = "SELECT employes.id, employes.name,employes.salary," +
                "departments.title,positions.position \n"+
                "FROM application.employes \n"+
                "JOIN application.departments ON employes.departments=departments.id \n"+
                "JOIN application.positions ON employes.position=positions.id \n"+
                "WHERE employes.id =" + idEmployee;
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
        ) {
            try(ResultSet resultSet = statement.executeQuery(sgl)){
                resultSet.next();
                long id = Long.parseLong(idEmployee);
                String name = resultSet.getString(2);
                Double salary = resultSet.getDouble(3);
                String title = resultSet.getString(4);
                String position = resultSet.getString(5);
                employee = new Employee(id, name, salary,title,position);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return employee;
    }



    public long getEmployeeCount () {
        long employeeCount = 0;
        String sql = "SELECT count(id)  FROM application.employes";
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
        ){
            try(ResultSet resultSet = statement.executeQuery(sql)){
                resultSet.next();
                 employeeCount = resultSet.getLong(1);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return employeeCount;
    }

    public List<Employee> getEmployeeList(String offset , String employeeInOnePage){
        List<Employee> listOfEmployees = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT id, name,salary \n"+
                    "FROM application.employes \n"+
                    "ORDER BY id ASC \n" +
                    "LIMIT "+ employeeInOnePage + " OFFSET "+ offset)){
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


    public List<Employee> getEmployeeListSortedByDepartment(String parameter){
        String sql = "SELECT employes.id, employes.name,employes.salary," +
                "departments.title,positions.position \n"+
                "FROM application.employes \n"+
                "JOIN application.departments ON employes.departments=departments.id \n"+
                "JOIN application.positions ON employes.position=positions.id \n" +
                "WHERE departments.id =" + parameter;
        List<Employee> listOfEmployees = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery(sql)){
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

    public List<Employee> getEmployeeListSortedByPosition(String parameter){
        String sql = "SELECT employes.id, employes.name,employes.salary," +
                "departments.title,positions.position \n"+
                "FROM application.employes \n"+
                "JOIN application.departments ON employes.departments=departments.id \n"+
                "JOIN application.positions ON employes.position=positions.id \n" +
                "WHERE positions.id =" + parameter;
        List<Employee> listOfEmployees = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery(sql)){
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

    public static DBEmployeeStorage getInstance() {
        return instance;
    }
}

