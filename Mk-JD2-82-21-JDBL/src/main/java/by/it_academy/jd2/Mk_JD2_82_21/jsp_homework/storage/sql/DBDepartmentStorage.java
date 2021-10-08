package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.sql;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IDepartmentDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.util.DataSource;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBDepartmentStorage implements IDepartmentDAO {
    private static DBDepartmentStorage instance = new DBDepartmentStorage();

    private DBDepartmentStorage() {
    }

    @Override
   public List<Department> getDepartmentList(){
       Department positions;
        List<Department> depatrmentList = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT id, title, parent_department FROM application.departments")){
                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String title = resultSet.getString(2);
                    long parentDepartmentId = resultSet.getLong(3);
                    if ( parentDepartmentId == 0) {
                         positions = new Department(id, title);
                    } else {
                        Department parentDepartment = getParentDepartment(parentDepartmentId);
                         positions = new Department(id, title, parentDepartment);
                    }
                    depatrmentList.add(positions);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return depatrmentList;
    }

    public Department getParentDepartment(long parentDepartmentId){
        String parentDepartmentString = String.valueOf(parentDepartmentId);
        Department ParentDepartment;
        String sql = "SELECT id, title  FROM application.departments WHERE id = " + parentDepartmentString;
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
        ){
            try(ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.next();
                Long id = resultSet.getLong(1);
                String title = resultSet.getString(2);
                ParentDepartment = new Department(id,title);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return ParentDepartment;
    }

    public static DBDepartmentStorage getInstance() {
        return instance;
    }
}
