package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class DBFiller {
    public static void main(String[] args) {
        String text;
        Random random = new Random();
        double salary = (double) random.nextInt(100);
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Lenovo\\Desktop\\Курсы\\namesFile.txt"))) {
            while ((text = reader.readLine()) != null) {
                System.out.println(text);
                System.out.println(salary);
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

       /* public static long addEmployee(String name, double salary,long departments,long position) {
            long id;
            try (Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/crm",
                    "postgres", "11609")
            ) {

                try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employes(\n" +
                        "name, salary, departments,position)\n" +
                        "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)
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
        }*/
    }

}
