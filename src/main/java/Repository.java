import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository {
    private static final String URL = MyUtils.getProperty("db.url");
    private static final String USERNAME = MyUtils.getProperty("db.username");
    private static final String PASSWORD = MyUtils.getProperty("db.password");

    private static Connection connection;

    static {
        try {
            Class.forName(MyUtils.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
