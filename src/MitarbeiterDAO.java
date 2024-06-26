import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterDAO {



    public static void main(String[] args) throws SQLException {

        Mitarbeiter mitarbeiter = new Mitarbeiter("Testmitarbeiter", 40, "versuch1", 1);


        updateMitarbeiter(mitarbeiter, "testabteilung420");
        //deleteMitarbeiter(2); //funktioniert
        //updateMitarbeiterByName("testMA", "testabteilung", "neueAbteilung2"); //funktioniert
        //updateMitarbeiter(mitarbeiter, "neueAbteiliung"); //funktioniert
        //deleteMitarbeiterByName("Ma5", "kündigungsabteilung");
        //addMitarbeiter(mitarbeiter); //funktioniert
        //getMitarbeiterById(2); //funktioniert
        //getMitarbeiterByName("testMA"); // funktioniert



        List<Mitarbeiter> mitarbeiterListe = getAllMitarbeiter(); //funktioniert
        for (Mitarbeiter m : mitarbeiterListe) {
            System.out.println(m);
        }





    }

    public static void addMitarbeiter(Mitarbeiter mitarbeiter) throws SQLException {
        String sql = "INSERT INTO mitarbeiter (nameMa, alterMa, abteilung, unternehmenID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mitarbeiter.getName());
            pstmt.setInt(2, mitarbeiter.getAlter());
            pstmt.setString(3, mitarbeiter.getAbteilung());
            pstmt.setInt(4, mitarbeiter.getUnternehmenID());
            pstmt.executeUpdate();
        }
    }

    public static Mitarbeiter getMitarbeiterById(int id) throws SQLException {
        String sql = "SELECT * FROM mitarbeiter WHERE mitarbeiterID  = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("mitarbeiterID: " + rs.getInt("mitarbeiterID") + "   nameMa: " + rs.getString("nameMa") + "   alterMa: " + rs.getInt("alterMa") + "   abteilung: " + rs.getString("abteilung") + "   unternehmenID: " + rs.getInt("unternehmenID"));
                return new Mitarbeiter(rs.getString("nameMa"), rs.getInt("alterMa"), rs.getString("abteilung"), rs.getInt("unternehmenID"));


            }
        }
        return null;
    }

    public static Mitarbeiter getMitarbeiterByName(String name) throws SQLException {
        String sql = "SELECT * FROM mitarbeiter WHERE nameMa  = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("mitarbeiterID: " + rs.getInt("mitarbeiterID") + "   nameMa: " + rs.getString("nameMa") + "   alterMa: " + rs.getInt("alterMa") + "   abteilung: " + rs.getString("abteilung") + "   unternehmenID: " + rs.getInt("unternehmenID"));
                return new Mitarbeiter(rs.getString("nameMa"), rs.getInt("alterMa"), rs.getString("abteilung"), rs.getInt("unternehmenID"));

            }
        }
        return null;
    }

    public static List<Mitarbeiter> getAllMitarbeiter() throws SQLException {
        List<Mitarbeiter> mitarbeiterList = new ArrayList<>();
        String sql = "SELECT * FROM mitarbeiter";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Mitarbeiter mitarbeiter = new Mitarbeiter(rs.getString("nameMa"), rs.getInt("alterMa"), rs.getString("abteilung"), rs.getInt("unternehmenID"));
                mitarbeiterList.add(mitarbeiter);
            }
        }

        //System.out.println(kundenList);
        return mitarbeiterList;
    }


    public static void updateMitarbeiter(Mitarbeiter mitarbeiter, String neueAbteilung) throws SQLException {
        String sql = "UPDATE mitarbeiter SET abteilung = ? WHERE nameMa = ? and abteilung = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, neueAbteilung);
            pstmt.setString(2, mitarbeiter.getName());
            pstmt.setString(3, mitarbeiter.getAbteilung());
            pstmt.executeUpdate();
        }
    }

    public static void updateMitarbeiterByName(String nameMitarbeiter, String abteilungMitarbeiter, String neueAnteilung) throws SQLException {
        String sql = "UPDATE mitarbeiter SET abteilung = ? WHERE nameMa = ? and abteilung = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, neueAnteilung);
            pstmt.setString(2, nameMitarbeiter);
            pstmt.setString(3, abteilungMitarbeiter);
            pstmt.executeUpdate();
        }
    }


    public static void deleteMitarbeiter(int id) throws SQLException {
        String sql = "DELETE FROM mitarbeiter WHERE mitarbeiterID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public static void deleteMitarbeiterByName(String name) throws SQLException {
        String sql = "DELETE FROM mitarbeiter WHERE nameMa = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);

            pstmt.executeUpdate();
        }
    }
    public static void deleteMitarbeiterByNameAndAbteilung(String name, String abteilung) throws SQLException {
        String sql = "DELETE FROM mitarbeiter WHERE nameMa = ? and abteilung = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, abteilung);
            pstmt.executeUpdate();
        }
    }



}
