package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySQLConection {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        String dbUrl= resourceBundle.getString("dbURL");
        String dbUser =resourceBundle.getString("dbUser");
        String dbPass =resourceBundle.getString("dbPass");
        return DriverManager.getConnection(dbUrl,dbUser,dbPass);

    }
}
