package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.DBService;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.DataSource;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;

import java.sql.*;

public class DBInitializer {
    private static DBInitializer instance = new DBInitializer();

    private DBInitializer(){
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

    public Employee getEmployee(String idEmployee){
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

    public static DBInitializer getInstance() {
        return instance;
    }
}

