package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.DBService;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.DataSource;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBDepartmentService {
    private static DBDepartmentService instance = new DBDepartmentService();

    private DBDepartmentService() {
    }

    public static DBDepartmentService getInstance() {
        return instance;
    }

   public List<Department> getDepartmentList(){
        List<Department> depatrmentList = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT id, title, parent_department FROM application.departments")){
                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String title = resultSet.getString(2);
                    long parentDepartmentId = resultSet.getLong(3);
                    String parentDepartment;
                    if ( parentDepartmentId == 0) {
                        parentDepartment = "Родительский отдел не указан";
                   } else {
                        parentDepartment = getParentDepartment(parentDepartmentId);
                    }
                    Department positions = new Department(id, title,parentDepartment);
                    depatrmentList.add(positions);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return depatrmentList;
    }

    public String getParentDepartment(long parentDepartmentId){
        String ParentDepartment;
        String sql = "SELECT id, title, parent_department FROM application.departments WHERE id = " + parentDepartmentId;
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
        ){
            try(ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.next();
                ParentDepartment = resultSet.getString(2);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return ParentDepartment;
    }
}
