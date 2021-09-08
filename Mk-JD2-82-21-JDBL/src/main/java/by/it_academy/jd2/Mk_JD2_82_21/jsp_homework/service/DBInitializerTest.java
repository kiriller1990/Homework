package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import java.sql.*;

public class DBInitializerTest {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Ошибка загрузки драйвера", e);
        }
    }

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/crm",
                "postgres", "11609"
        );
             Statement statement = con.createStatement();) {
           // String name = "Антон";
           // Double salary = -1.0;

           /* try (PreparedStatement preparedStatement =con.prepareStatement("INSERT INTO application.employes(\n" +
                    "\t name, salary)\n" +
                   "\tVALUES (?,?);")
            ){
                preparedStatement.setString(1,name);
                preparedStatement.setDouble(2,salary);

            }*/
            try ( ResultSet resultSet = statement.executeQuery("SELECT id,name,salary FROM application.employes ORDER BY id ASC");) {
                System.out.printf("id\tИмя\tЗарплата\n");
                  // try( ResultSet resultSet = statement.executeQuery("SELECT id,name,salary FROM application.employes ORDER BY id ASC");) {
               // System.out.printf("id\tИмя\tЗарплата\n");
                while ((resultSet.next())) {
                    System.out.printf("%d\t%s\t%,.2f\n", resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3));
                }
            }
        }catch (SQLException e) {
                throw new IllegalStateException("Cервер не запущен", e);

            }
        }
    }