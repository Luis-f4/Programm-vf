import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnternehmenDAO {

    public static void main(String[] args) throws SQLException {
        //getUnternehmenById(2); //funktionier
        getUnternehmenByName("testunternehmen2"); //funktionier
        deleteUnternehmenByName("testunternehmen2"); //funktionier
        //Unternehmen unternehmen = new Unternehmen("testunternehmen2");
        //addUnternehmen(unternehmen); //funktionier


        //deleteUnternehmen(5); //funktionier
    }

    public static void addUnternehmen(Unternehmen unternehmen) throws SQLException {
        String sql = "INSERT INTO unternehmen (nameUnternehmen) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, unternehmen.getName());
            pstmt.executeUpdate();
        }
    }

    public static Unternehmen getUnternehmenById(int id) throws SQLException {
        String sql = "SELECT * FROM unternehmen WHERE unternehmenID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                //System.out.println("name: " + rs.getString("nameUnternehmen") + "   id: " + rs.getInt("unternehmenID"));
                return new Unternehmen(rs.getString("nameUnternehmen"), rs.getInt("unternehmenID"));
            }
        }
        return null;
    }

    public static int getUnternehmenId(String name) throws SQLException {
        String sql = "SELECT * FROM unternehmen WHERE nameUnternehmen = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                //System.out.println("name: " + rs.getString("nameUnternehmen") + "   id: " + rs.getInt("unternehmenID"));
                return rs.getInt("unternehmenID");
            }
        }
        return -1;
    }

    public static Unternehmen getUnternehmenByName(String name) throws SQLException {
        String sql = "SELECT * FROM unternehmen WHERE nameUnternehmen = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                //System.out.println("name2: " + rs.getString("nameUnternehmen") + "   id2: " + rs.getInt("unternehmenID"));
                return new Unternehmen(rs.getString("nameUnternehmen"), rs.getInt("unternehmenID"));
            }
        }
        return null;
    }

    public List<Unternehmen> getAllUnternehmen() throws SQLException {
        List<Unternehmen> unternehmenList = new ArrayList<>();
        String sql = "SELECT * FROM unternehmen";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Unternehmen unternehmen = new Unternehmen(rs.getString("nameUnternehmen"), rs.getInt("unternehmenID"));
                unternehmenList.add(unternehmen);
            }
        }
        return unternehmenList;
    }

    public void updateUnternehmen(Unternehmen unternehmen) throws SQLException {
        String sql = "UPDATE unternehmen SET nameUnternehmen = ? WHERE unternehmenID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, unternehmen.getName());
            pstmt.setInt(2, unternehmen.getUnternehmenID());
            pstmt.executeUpdate();
        }
    }

    public static void deleteUnternehmen(int id) throws SQLException {
        String sql = "DELETE FROM unternehmen WHERE unternehmenID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public static void deleteUnternehmenByName(String name) throws SQLException {
        String sql = "DELETE FROM unternehmen WHERE nameUnternehmen = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }
}
