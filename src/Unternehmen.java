

import java.util.ArrayList;
import java.util.List;



public class Unternehmen {

    private String name;
    private int unternehmenID;
    private List<Mitarbeiter> mitarbeiter;
    private List<Fuehrungskraft> fuehrungskraft;
    private List<Produkt> produkt;
    private List<Kunde> kunden;

    public Unternehmen(String name) {
        mitarbeiter = new ArrayList<Mitarbeiter>();
        fuehrungskraft = new ArrayList<Fuehrungskraft>();
        produkt = new ArrayList<Produkt>();
        kunden = new ArrayList<Kunde>();
        this.name = name;
    }

    public Unternehmen(String name, int unternehmenID) {
        mitarbeiter = new ArrayList<Mitarbeiter>();
        fuehrungskraft = new ArrayList<Fuehrungskraft>();
        produkt = new ArrayList<Produkt>();
        kunden = new ArrayList<Kunde>();
        this.name = name;
        this.unternehmenID = unternehmenID;
    }


    public int getUnternehmenID(){
        return this.unternehmenID;
    }
    public String getName(){
        return this.name;
    }

    public void addMitarbeiter(Mitarbeiter mitarbeiterV) {
        mitarbeiter.add(mitarbeiterV);
    }
    public List<Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    public List<String> getMitarbeiterNamen() {
        List<String> namen = new ArrayList<String>();
        for (Mitarbeiter ma : mitarbeiter) {
            namen.add(ma.getName());
        }
        return namen;
    }

    /*
    public List<String> getMitarbeiter() {

  }*/


    public void removeMitarbeiter(Mitarbeiter mitarbeiterV) {
        mitarbeiter.remove(mitarbeiterV);
    }

    public void addFuehrungskraft(Fuehrungskraft fuehrungskraftV) {
        fuehrungskraft.add(fuehrungskraftV);
    }

    public List<Fuehrungskraft> getFuehrungskraft() {
        return fuehrungskraft;
    }

    public void removeFuehrungskraft(Fuehrungskraft fuehrungskraftV) {
        fuehrungskraft.remove(fuehrungskraftV);
    }


    public void addKunde(Kunde kunde) {
        kunden.add(kunde);
    }

    public List<Kunde> getKunden() {
        return kunden;
    }

    public void removeKunde(Kunde kunde) {

        kunde.clearVertraege();

        kunden.remove(kunde);
    }


    // Methoden für Komposition
    //(Kategorie kategorie, double preis, String name, Unternehmen unternehmen)
    public void addProduct(Produkt.Kategorie kategorie, double preis, String name, Unternehmen unternehmen) {
        Produkt product = new Produkt(kategorie, preis, name, unternehmen);
        produkt.add(product);
    }

    /*public List<Produkt> getProdukte() {

      return produkt;
  }*/


    public List<String> getProdukte() {
        List<String> namen = new ArrayList<String>();

        for (Produkt pro : produkt) {
            String parkStr = "";
            parkStr = pro.getName();
            parkStr += " Preis: ";
            parkStr += pro.getPreis();
            parkStr += "€";
            namen.add(parkStr);
        }
        return namen;
    }

    public Produkt getProdukteByName(String name, Produkt.Kategorie kategorie) {

        for(Produkt produktZ : produkt){
            if(produktZ.getKategorie().equals(kategorie) && produktZ.getName().equals(name)){
                return produktZ;

            }
        }

        System.out.println("Es wurde kein Produkt namens: " + name + " gefunden");
        return null;
    }


    public void removeProduct(Produkt product) {
        produkt.remove(product);
    }

    public void clearProducts() {
        produkt.clear();
    }


    public  boolean checkObKundeExistiert(Kunde neuerKunde){

        for(Kunde kunde : kunden){
            if(kunde.equals(neuerKunde)){
                return true;
            }
        }

        return false;
    }

    public  boolean checkObMaExistiert(Mitarbeiter ma){

        for(Mitarbeiter mitar : mitarbeiter){
            if(mitar.equals(ma)){
                return true;
            }
        }

        return false;
    }

/*
    @Override
    protected void finalize() throws Throwable {
        // Komposition sicherstellen: Produkte werden entfernt, wenn das Unternehmen zerstört wird
        try {
            clearProducts();
        } finally {
            super.finalize();
        }
    }*/
}