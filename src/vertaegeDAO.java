import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class vertaegeDAO {



    public static void main(String[] args) throws SQLException {


        addVertrag("Franz", "Beispiel Produkt3");

        deleteVertragByName("Franz", "Beispiel Produkt3");



    }

    public static void addVertrag(String nameKunde, String nameProdukt) throws SQLException {
        String sql = "INSERT INTO vertaege (produktID, kundeID) VALUES (?, ?)";

        Kunde kunde = KundeDAO.getKundeByName(nameKunde);
        //Produkt produkt = ProduktDAO.getProduktByName(nameProdukt);

        int produktID = ProduktDAO.getProduktId(nameProdukt);
        int kundeID = KundeDAO.getKundeId(nameKunde);

        try (Connection conn = DatabaseConnection.getConnection();



             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, produktID);
            pstmt.setInt(2, kundeID);
            pstmt.executeUpdate();
        }
    }

    /*
    public static Mitarbeiter getVergratById(int id) throws SQLException {
        String sql = "SELECT * FROM vertaege WHERE vertragsID  = ?";
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

     */
/*
    public static Mitarbeiter getMitarbeiterByName(String nameKunde, String nameProdukt ) throws SQLException {
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
 */

    public static void deleteVertrag(int id) throws SQLException {
        String sql = "DELETE FROM vertaege WHERE vertragsID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public static void deleteVertragByName(String nameKunde, String nameProdukt) throws SQLException {
        String sql = "DELETE FROM vertaege WHERE produktID = ? and kundeID = ?";

        int produktID = ProduktDAO.getProduktId(nameProdukt);
        int kundeID = KundeDAO.getKundeId(nameKunde);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, produktID);
            pstmt.setInt(2, kundeID);
            pstmt.executeUpdate();
        }
    }





}




