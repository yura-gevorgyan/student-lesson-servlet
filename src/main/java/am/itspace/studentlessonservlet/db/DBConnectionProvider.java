package am.itspace.studentlessonservlet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {

    private DBConnectionProvider() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static DBConnectionProvider provider = null;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/student_lesson";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static DBConnectionProvider getProvider() {
        if (provider == null) {
            return new DBConnectionProvider();
        }
        return provider;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
