import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class ProduktDAO {


    public static void main(String[] args) throws SQLException {



        Unternehmen unternehmen = UnternehmenDAO.getUnternehmenByName("vodafone");

        //System.out.println("hier das produkt lololol: " + getProduktByNameAndKategorie("Beispiel Produkt3", "INTERNET_PHONE"));



        if (unternehmen != null) {
            Produkt produkt = new Produkt(Produkt.Kategorie.INTERNET_PHONE, 50.00, "Beispiel Produkt3", unternehmen);
            //addProdukt(produkt); //funktioniert

            /*
            getProduktById(1); //funktioniert
            getProduktByName("Beispiel Produkt2"); //funktioniert

             */
        } else {
            System.out.println("Unternehmen nicht gefunden.");
        }


/*
        List<Produkt> produkteList = getAllProdukte(); //funktioniert


        for (Produkt produkt : produkteList) {
            System.out.println(produkt);
        }

        deleteProdukt(2); //funktioniert
        deleteProduktByName("Beispiel Produkt3", "INTERNET_PHONE");  //funktioniert

 */


    }




    public static void addProdukt(Produkt produkt) throws SQLException { //funktioniert
        String sql = "INSERT INTO produkte (kategorie, name, preis, unternehmenID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produkt.getKategorieAsString());
            pstmt.setString(2, produkt.getName());
            pstmt.setDouble(3, produkt.getPreis());
            pstmt.setInt(4, produkt.getUnternehmen().getUnternehmenID());
            pstmt.executeUpdate();
        }
    }


    public static int getProduktId(String name) throws SQLException {
        String sql = "SELECT produktID FROM produkte WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("produktID");
            }
        }
        return -1;
    }

    public static Produkt getProduktById(int id) throws SQLException {
        String sql = "SELECT * FROM produkte WHERE produktID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                //System.out.println("name: " + rs.getString("name") + "   unternehmenID: " + rs.getInt("unternehmenID") + "   preis: " + rs.getDouble("preis") + "   Kategorie: " + rs.getString("kategorie"));

                String kategorieStr = rs.getString("kategorie");
                Produkt.Kategorie kategorie = Produkt.Kategorie.valueOf(kategorieStr);

                return new Produkt(kategorie, rs.getDouble("preis"), rs.getString("name"), UnternehmenDAO.getUnternehmenById(rs.getInt("unternehmenID")), rs.getInt("unternehmenID"));
            }
        }
        return null;
    }

    public static Produkt getProduktByName(String name) throws SQLException {
        String sql = "SELECT * FROM produkte WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("name: " + rs.getString("name") + "   unternehmenID: " + rs.getInt("unternehmenID") + "   preis: " + rs.getDouble("preis") + "   Kategorie: " + rs.getString("kategorie"));

                String kategorieStr = rs.getString("kategorie");
                Produkt.Kategorie kategorie = Produkt.Kategorie.valueOf(kategorieStr);

                return new Produkt(kategorie, rs.getDouble("preis"), rs.getString("name"), UnternehmenDAO.getUnternehmenById(rs.getInt("unternehmenID")), rs.getInt("unternehmenID"));
            }
        }
        return null;
    }

    public static Produkt getProduktByNameAndKategorie(String name, String kategorie111) throws SQLException {
        String sql = "SELECT * FROM produkte WHERE name = ? and kategorie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, kategorie111);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("name: " + rs.getString("name") + "   unternehmenID: " + rs.getInt("unternehmenID") + "   preis: " + rs.getDouble("preis") + "   Kategorie: " + rs.getString("kategorie"));

                String kategorieStr = rs.getString("kategorie");
                Produkt.Kategorie kategorie = Produkt.Kategorie.valueOf(kategorieStr);

                return new Produkt(kategorie, rs.getDouble("preis"), rs.getString("name"), UnternehmenDAO.getUnternehmenById(rs.getInt("unternehmenID")), rs.getInt("unternehmenID"));
            }
        }
        return null;
    }

    public static List<Produkt> getAllProdukte() throws SQLException {
        List<Produkt> ProdukteList = new ArrayList<>();
        String sql = "SELECT * FROM produkte";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String kategorieStr = rs.getString("kategorie");
                Produkt.Kategorie kategorie = Produkt.Kategorie.valueOf(kategorieStr);

                Produkt produkt = new Produkt(kategorie, rs.getDouble("preis"), rs.getString("name"), UnternehmenDAO.getUnternehmenById(rs.getInt("unternehmenID")), rs.getInt("unternehmenID"));
                ProdukteList.add(produkt);
            }
        }
        return ProdukteList;
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
    } */

    public static void deleteProdukt(int id) throws SQLException {
        String sql = "DELETE FROM produkte WHERE produktID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public static void deleteProduktByName(String name, String kategorie) throws SQLException {
        String sql = "DELETE FROM produkte WHERE name = ? and kategorie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, kategorie);
            pstmt.executeUpdate();
        }
    }

}
