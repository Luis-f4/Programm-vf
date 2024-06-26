import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/vf-programm";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            // Prüfen, ob der Treiber geladen werden kann
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Treiber erfolgreich geladen.");
        } catch (ClassNotFoundException e) {
            //System.out.println("Treiber konnte nicht geladen werden.");
            e.printStackTrace();
            return null;
        }

        try {
            // Verbindung herstellen
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("Verbindung erfolgreich hergestellt.");
            return connection;
        } catch (SQLException e) {
            //System.out.println("Verbindung konnte nicht hergestellt werden.");
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                System.out.println("Verbindung ist nicht null.");
            } else {
                System.out.println("Verbindung ist null.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Ausnahme aufgetreten.");
            e.printStackTrace();
        }
    }
}
