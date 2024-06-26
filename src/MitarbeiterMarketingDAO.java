import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MitarbeiterMarketingDAO {


    public static void main(String[] args) throws SQLException {




        //MitarbeiterMarketing mitarbeiterMarketing = new MitarbeiterMarketing("MaM2", 40, "testabteilung2", 1);
        //addMitarbeiterMarketing(mitarbeiterMarketing); // Funktioniert

        deleteMitarbeiter(1); // funktioniert

        //getMitarbeiterMarketingById(1); // funktioniert
        //getMitarbeiterByName("MaM1"); // Funktioniert

/*
        List<MitarbeiterMarketing> mitarbeiterMarketingListe = getAllMitarbeiterMarketing(); //funktioniert
        for (MitarbeiterMarketing m : mitarbeiterMarketingListe) {
            System.out.println(m);
        }

 */




    }



    public static void addMitarbeiterMarketing(MitarbeiterMarketing mitarbeiterMarketing) throws SQLException {
        String sql = "INSERT INTO mitarbeitermarketing (name, alterMaMarketing, abteilung, unternehmenID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mitarbeiterMarketing.getName());
            pstmt.setInt(2, mitarbeiterMarketing.getAlter());
            pstmt.setString(3, mitarbeiterMarketing.getAbteilung());
            pstmt.setInt(4, mitarbeiterMarketing.getUnternehmenID());
            pstmt.executeUpdate();
        }
    }

    public static MitarbeiterMarketing getMitarbeiterMarketingById(int id) throws SQLException {
        String sql = "SELECT * FROM mitarbeitermarketing WHERE mitarbeiterMarketingID   = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("mitarbeiterMarketingID: " + rs.getInt("mitarbeiterMarketingID") + "   name: " + rs.getString("name") + "   alterMaMarketing: " + rs.getInt("alterMaMarketing") + "   abteilung: " + rs.getString("abteilung") + "   unternehmenID: " + rs.getInt("unternehmenID"));
                return new MitarbeiterMarketing(rs.getString("name"), rs.getInt("alterMaMarketing"), rs.getString("abteilung"), rs.getInt("unternehmenID"));


            }
        }
        return null;
    }

    public static Mitarbeiter getMitarbeiterByName(String name) throws SQLException {
        String sql = "SELECT * FROM mitarbeitermarketing WHERE name  = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("mitarbeiterMarketingID: " + rs.getInt("mitarbeiterMarketingID") + "   name: " + rs.getString("name") + "   alterMaMarketing: " + rs.getInt("alterMaMarketing") + "   abteilung: " + rs.getString("abteilung") + "   unternehmenID: " + rs.getInt("unternehmenID"));
                return new MitarbeiterMarketing(rs.getString("name"), rs.getInt("alterMaMarketing"), rs.getString("abteilung"), rs.getInt("unternehmenID"));

            }
        }
        return null;
    }

    public static List<MitarbeiterMarketing> getAllMitarbeiterMarketing() throws SQLException {
        List<MitarbeiterMarketing> MitarbeiterMarketingList = new ArrayList<>();
        String sql = "SELECT * FROM mitarbeitermarketing";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                MitarbeiterMarketing mitarbeiter = new MitarbeiterMarketing(rs.getString("name"), rs.getInt("alterMaMarketing"), rs.getString("abteilung"), rs.getInt("unternehmenID"));

                MitarbeiterMarketingList.add(mitarbeiter);
            }
        }

        //System.out.println(kundenList);
        return MitarbeiterMarketingList;
    }

/*
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




    public static void deleteMitarbeiter(int id) throws SQLException {
        String sql = "DELETE FROM mitarbeitermarketing WHERE mitarbeiterMarketingID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    /*
    public static void deleteMitarbeiterByName(String name, String abteilung) throws SQLException {
        String sql = "DELETE FROM mitarbeitermarketing WHERE name = ? and abteilung = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, abteilung);
            pstmt.executeUpdate();
        }
    }*/



}
