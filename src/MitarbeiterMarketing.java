
public class MitarbeiterMarketing extends Mitarbeiter{


    public MitarbeiterMarketing(String name, int alter, String abteilung) {
        super(name, alter, abteilung);
    }

    public MitarbeiterMarketing(String name, int alter, String abteilung, int unternehmenID) {
        super(name, alter, abteilung, unternehmenID);
    }

    public MitarbeiterMarketing(String name, int alter, String abteilung, Unternehmen unternehmen, int unternehmenID) {
        super(name, alter, abteilung, unternehmen, unternehmenID);
    }

    public void neueProdukteAnbieten(Unternehmen unternehmen, Produkt.Kategorie kategorie, double preis, String name){
        unternehmen.addProduct(kategorie, preis, name, unternehmen);
    }


}

