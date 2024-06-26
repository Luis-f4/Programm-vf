

public class Produkt {
    public enum Kategorie {
        MOBILFUNK,
        INTERNET_PHONE,
        TV
    }

    private Kategorie kategorie;
    private double preis;
    private String name;
    private Unternehmen unternehmen;
    private int unternehmenID;

    public Produkt(Kategorie kategorie, double preis, String name, Unternehmen unternehmen) {
        this.kategorie = kategorie;
        this.preis = preis;
        this.name = name;
        this.unternehmen = unternehmen;
    }


    public Produkt(Kategorie kategorie, double preis, String name, Unternehmen unternehmen, int unternehmenID) {
        this.kategorie = kategorie;
        this.preis = preis;
        this.name = name;
        this.unternehmen = unternehmen;
        this.unternehmenID = unternehmenID;
    }






    public int getUnternehmenID(){
        return  this.unternehmenID;
    }
    public Kategorie getKategorie() {
        return kategorie;
    }
    public String getKategorieAsString() {
        return kategorie.name();  // Rückgabe als String
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public double getPreis() {
        return preis;
    }


    public void setPreis(double preis) {
        this.preis = preis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unternehmen getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(Unternehmen unternehmen) {
        this.unternehmen = unternehmen;
    }


    public String toString() {
        return "Produkt [Kategorie=" + kategorie + ", Preis=" + preis + ", Name=" + name + ", Unternehmen=" + unternehmen.getName() + "]";
    }
}
