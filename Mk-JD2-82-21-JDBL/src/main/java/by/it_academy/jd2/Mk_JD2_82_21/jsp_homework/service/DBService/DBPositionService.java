package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.DBService;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.DataSource;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Position;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBPositionService {
    private static DBPositionService instance = new DBPositionService();

    private DBPositionService(){
    }

    public static DBPositionService getInstance() {
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

