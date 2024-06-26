import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FuehrungskraftDAO {


    public static void main(String[] args) throws SQLException {


        //deleteFuehurngskraft(3); // Funktioniert
        //deleteMitarbeiterByName("CEO");  // Funktioniert
/*
        Fuehrungskraft fuehrungskraft = new Fuehrungskraft("CEO", 50,1);
        Fuehrungskraft fuehrungskraft2 = new Fuehrungskraft("CFO", 50,1);
        addFuehrungskraft(fuehrungskraft);  //Funktioniert
        addFuehrungskraft(fuehrungskraft2);*/


        //getFuehrungskraftById(1); //Funktioniert
        //getFuehrungskraftByName("Cheffe");  //Funktioniert

/*
        List<Fuehrungskraft> fuehrungskraftListe = getAllFuehrungskraft(); //funktioniert
        for (Fuehrungskraft f : fuehrungskraftListe) {
            System.out.println(f);
        }

 */





    }


    public static void addFuehrungskraft(Fuehrungskraft fuehrungskraft) throws SQLException {
        String sql = "INSERT INTO fuehrungskraft (name, alterFuehrungskraft, unternehmenID) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fuehrungskraft.getName());
            pstmt.setInt(2, fuehrungskraft.getAlter());
            pstmt.setInt(3, fuehrungskraft.getUnternehmenID());
            pstmt.executeUpdate();
        }
    }

    public static Fuehrungskraft getFuehrungskraftById(int id) throws SQLException {
        String sql = "SELECT * FROM fuehrungskraft WHERE fuehrungskraftID   = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("fuehrungskraftID: " + rs.getInt("fuehrungskraftID") + "   name: " + rs.getString("name") + "   alterFuehrungskraft: " + rs.getInt("alterFuehrungskraft") +"  unternehmenID: " + rs.getInt("unternehmenID"));

                Unternehmen unternehmen = UnternehmenDAO.getUnternehmenById(rs.getInt("unternehmenID"));


                return new Fuehrungskraft(rs.getString("name"), rs.getInt("alterFuehrungskraft"), unternehmen,  rs.getInt("unternehmenID"));
            }
        }
        return null;
    }

    public static Fuehrungskraft getFuehrungskraftByName(String name) throws SQLException {
        String sql = "SELECT * FROM fuehrungskraft WHERE name   = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("fuehrungskraftID: " + rs.getInt("fuehrungskraftID") + "   name: " + rs.getString("name") + "   alterFuehrungskraft: " + rs.getInt("alterFuehrungskraft") +"  unternehmenID: " + rs.getInt("unternehmenID"));

                Unternehmen unternehmen = UnternehmenDAO.getUnternehmenById(rs.getInt("unternehmenID"));


                return new Fuehrungskraft(rs.getString("name"), rs.getInt("alterFuehrungskraft"), unternehmen,  rs.getInt("unternehmenID"));
            }
        }
        return null;
    }

    public static List<Fuehrungskraft> getAllFuehrungskraft() throws SQLException {
        List<Fuehrungskraft> fuehrungskraftList = new ArrayList<>();
        String sql = "SELECT * FROM fuehrungskraft";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Unternehmen unternehmen = UnternehmenDAO.getUnternehmenById(rs.getInt("unternehmenID"));
                Fuehrungskraft fuehrungskraft = new Fuehrungskraft(rs.getString("name"), rs.getInt("alterFuehrungskraft"), unternehmen,  rs.getInt("unternehmenID"));
                fuehrungskraftList.add(fuehrungskraft);
            }
        }

        //System.out.println(kundenList);
        return fuehrungskraftList;
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


    public static void deleteFuehurngskraft(int id) throws SQLException {
        String sql = "DELETE FROM fuehrungskraft WHERE fuehrungskraftID  = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }



    public static void deleteMitarbeiterByName(String name) throws SQLException {
        String sql = "DELETE FROM fuehrungskraft WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }


}
