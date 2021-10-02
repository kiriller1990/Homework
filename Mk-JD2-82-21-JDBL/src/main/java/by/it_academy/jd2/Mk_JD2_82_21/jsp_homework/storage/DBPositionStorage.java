package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.util.DataSource;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Position;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBPositionStorage {
    private static DBPositionStorage instance = new DBPositionStorage();

    private DBPositionStorage(){
    }

    public static DBPositionStorage getInstance() {
        return instance;
    }

    public List<Position> getPositionList(){
        List<Position> listOfPositions = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT id, position FROM application.positions")){

                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String position = resultSet.getString(2);

                    Position positions = new Position(id, position);
                    listOfPositions.add(positions);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return listOfPositions;
    }
}

