
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Establishes a connection between the Java application
 * and the MySQL database.
 */
public class DBConnection {

    /**
     * Reads database settings from db.properties
     * and establishes a MySQL connection.
     */
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        try (FileInputStream file = new FileInputStream("db.properties")) {
            properties.load(file);
        } catch (IOException e) {
            throw new SQLException(
                    "Cannot read db.properties. Make sure the file exists in the project root.",
                    e
            );
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        if (url == null || user == null || password == null) {
            throw new SQLException(
                    "Missing db.url, db.user, or db.password in db.properties."
            );
        }

        return DriverManager.getConnection(url, user, password);
    }
}