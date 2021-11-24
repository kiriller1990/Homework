package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private DataSource dataSource;

    public TestController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping
    public List<String> get(){
        List<String> names = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("SELECT first_name FROM test2.employee")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while(rs.next()){
                        names.add(rs.getString(1));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return names;
    }
}
