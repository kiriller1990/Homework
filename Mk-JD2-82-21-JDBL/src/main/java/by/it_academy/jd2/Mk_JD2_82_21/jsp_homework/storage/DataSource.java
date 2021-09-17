package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public  class DataSource {
    private static final ComboPooledDataSource cpds;

    static {
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException ex) {
            throw new RuntimeException("Ошибка драйвера загрузки", ex);
        }
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/crm");
        cpds.setUser("postgres");
        cpds.setPassword("11609");
    }

    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }


}
