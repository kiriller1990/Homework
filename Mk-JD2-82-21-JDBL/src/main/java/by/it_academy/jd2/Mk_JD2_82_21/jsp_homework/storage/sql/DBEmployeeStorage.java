package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.sql;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IEmployeeDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.util.DataSource;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBEmployeeStorage implements IEmployeeDAO {
    private static DBEmployeeStorage instance = new DBEmployeeStorage();

    private DBEmployeeStorage(){
    }


    public long addEmployee(String name, double salary,long department,long position) {
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

    @Override
    public long addEmployee(Employee employee) {
        long id;
        try (Connection con = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employes(\n" +
                    "name, salary, departments, position)\n" +
                    "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)
            ) {
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setDouble(2, employee.getSalary());
                preparedStatement.setLong(3, employee.getDepartment().getId());
                preparedStatement.setLong(4, employee.getPosition().getId());
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

    @Override
    public Employee getEmployeeCard(String idEmployee){
        Employee employee;
        String sgl = "SELECT employes.id, employes.name,employes.salary," +
                "departments.id,departments.title,positions.id,positions.position \n"+
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
                Long departmentId = resultSet.getLong(4);
                String title = resultSet.getString(5);
                Long positionId = resultSet.getLong(6);
                String employeePosition = resultSet.getString(7);
                Department department = new Department(departmentId,title);
                Position position = new Position(positionId,employeePosition);
                employee = new Employee(id, name, salary,department,position);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return employee;
    }


    @Override
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

    @Override
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

    @Override
    public List<Employee> getEmployeeListSortedByDepartment(String parameter){
        String sql = "SELECT employes.id, employes.name,employes.salary," +
                "departments.id,departments.title,positions.id,positions.position \n"+
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
                    Double salary = resultSet.getDouble(3);
                    Long departmentId = resultSet.getLong(4);
                    String title = resultSet.getString(5);
                    Long positionId = resultSet.getLong(6);
                    String employeePosition = resultSet.getString(7);

                    Department department = new Department(departmentId,title);
                    Position position = new Position(positionId,employeePosition);
                    Employee employee = new Employee(id, name, salary,department,position);

                    listOfEmployees.add(employee);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return listOfEmployees;
    }

    @Override
    public List<Employee> getEmployeeListSortedByPosition(String parameter){
        String sql = "SELECT employes.id, employes.name,employes.salary," +
                "departments.id,departments.title,positions.id,positions.position \n"+
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
                    Double salary = resultSet.getDouble(3);
                    Long departmentId = resultSet.getLong(4);
                    String title = resultSet.getString(5);
                    Long positionId = resultSet.getLong(6);
                    String employeePosition = resultSet.getString(7);

                    Department department = new Department(departmentId,title);
                    Position position = new Position(positionId,employeePosition);
                    Employee employee = new Employee(id, name, salary,department,position);

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

