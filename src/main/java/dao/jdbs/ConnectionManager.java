package dao.jdbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
   private static final String user = "postgres";
    private static final String password = "123321rus";
    private static final String url = "jdbc:postgresql://localhost:5432/skypro";
    private ConnectionManager(){
    }
    public static Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(
                url,
                user,
                password);
    }
}
