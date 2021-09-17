package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.DBService;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.DBService.DBInitializer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DBFiller {
    public static void main(String[] args) {
        String text;
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Home\\ermolovich\\dev\\Mk-JD2-82-21-JDBL\\src\\main\\java\\by\\it_academy\\jd2\\Mk_JD2_82_21\\jsp_homework\\storage\\employeesList.txt"))) {
            while ((text = reader.readLine()) != null) {
                double salary = Math.random()*100000000;
                long department = (long) (Math.random()*(6-1))+ 1;
                long position = (long) (Math.random()*(11-1))+ 1;
                DBInitializer.getInstance().addEmployee(text,salary,department,position);
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
