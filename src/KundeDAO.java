import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KundeDAO {


    public static void main(String[] args) throws SQLException {

        Kunde kunde = new Kunde("Franz", 30, 1);
        //System.out.println(getKundeId("Franz"));
        //addKunde(kunde); //funktioniert
        //getKunddeById(1); //funktioniert
        //getKundeByName("Max"); //funktioniert

        /*
        List<Kunde> kundenListe = getAllKunden(); //funktioniert
        for (Kunde k : kundenListe) {
            System.out.println(k);
        }
         */

        //System.out.println(getAllKunden());


        //deleteUnternehmen(2); //funktionier
        //deleteUnternehmenByName("Max");
    }

    public static void addKunde(Kunde kunde) throws SQLException {
        String sql = "INSERT INTO kunde (nameKunde, alterKunde, unternehmenID) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kunde.getName());
            pstmt.setInt(2, kunde.getAlter());
            pstmt.setInt(3, kunde.getUnternehmenID());
            pstmt.executeUpdate();
        }
    }

    public static void fillKundenVertraege(Kunde kunde, int kundenID) throws SQLException {
        String sql = "SELECT produktID FROM vertaege WHERE kundeID = ?";
        List<Integer> produktIDList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, kundenID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                produktIDList.add(rs.getInt("produktID"));
            }
        }


        weiterVerarbeiten(kunde, produktIDList);
    }

    private static void weiterVerarbeiten(Kunde kunde, List<Integer> produktIDList) throws SQLException {

        for (int produktID : produktIDList) {
            boolean exists = false;


            for (Produkt p : kunde.getVertraege()) {
                if (p.getName().equals(ProduktDAO.getProduktById(produktID).getName())) {
                    exists = true;
                    break;
                }
            }


            if (!exists) {
                kunde.addVertrag(ProduktDAO.getProduktById(produktID));
            }
        }
    }

    public static int getKundeId(String name) throws SQLException {
        String sql = "SELECT kundeID FROM kunde WHERE nameKunde = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("kundeID");
            }
        }
        return -1;
    }

    public static Kunde getKunddeById(int id) throws SQLException {
        String sql = "SELECT * FROM kunde WHERE kundeID  = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("kundeID: " + rs.getInt("kundeID") + "   nameKunde: " + rs.getString("nameKunde") + "   alterKunde: " + rs.getInt("alterKunde") + "   UnternehmenID: " + rs.getInt("UnternehmenID"));
                return new Kunde(rs.getString("nameKunde"), rs.getInt("alterKunde"), rs.getInt("UnternehmenID"));
            }
        }
        return null;
    }

    public static Kunde getKundeByName(String name) throws SQLException {
        String sql = "SELECT * FROM kunde WHERE nameKunde  = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                //System.out.println("kundeID: " + rs.getInt("kundeID") + "   nameKunde: " + rs.getString("nameKunde") + "   alterKunde: " + rs.getInt("alterKunde") + "   UnternehmenID: " + rs.getInt("UnternehmenID"));
                return new Kunde(rs.getString("nameKunde"), rs.getInt("alterKunde"), rs.getInt("UnternehmenID"));
            }
        }
        return null;
    }

    public static List<Kunde> getAllKunden() throws SQLException {
        List<Kunde> kundenList = new ArrayList<>();
        String sql = "SELECT * FROM kunde";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Kunde kunde = new Kunde(rs.getString("nameKunde"), rs.getInt("alterKunde"), rs.getInt("unternehmenID"));
                kundenList.add(kunde);
            }
        }

        //System.out.println(kundenList);
        return kundenList;
    }

    /*
    public void updateUnternehmen(Unternehmen unternehmen) throws SQLException {
        String sql = "UPDATE unternehmen SET nameUnternehmen = ? WHERE unternehmenID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, unternehmen.getName());
            pstmt.setInt(2, unternehmen.getUnternehmenID());
            pstmt.executeUpdate();
        }
    }*/

    public static void deleteKunde(int id) throws SQLException {
        String sql = "DELETE FROM kunde WHERE kundeID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public static void deleteKundeByName(String name) throws SQLException {
        String sql = "DELETE FROM kunde WHERE nameKunde = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }
}
