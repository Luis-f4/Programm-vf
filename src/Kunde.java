

import java.util.ArrayList;
import java.util.List;

public class Kunde extends Mensch{

    private List<Produkt> produkt;
    private int unternehmenID;
    public Kunde(String name, int alter) {
        super(name, alter);
        produkt = new ArrayList<Produkt>();

    }

    public Kunde(String name, int alter, int unternehmenID) {
        super(name, alter);
        produkt = new ArrayList<Produkt>();
        this.unternehmenID = unternehmenID;
    }
    public Kunde(String name, int alter, Unternehmen unternehmen, int unternehmenID) {
        super(name, alter);
        produkt = new ArrayList<Produkt>();
        unternehmen.addKunde(this);
        this.unternehmenID = unternehmenID;
    }

    public int getUnternehmenID(){
        return  this.unternehmenID;
    }

    public String toString() {
        return "Kunde{name='" + getName() + "', alter=" + getAlter() + ", unternehmenID=" + getUnternehmenID() + "}";
    }

    public List<Produkt> getVertraege(){
        return produkt;
    }

    public void addVertrag(Produkt neuesProdukt){
        produkt.add(neuesProdukt);
    }

    public void clearVertraege(){
        produkt.clear();
    }

    public double getMonatlicheKosten(){

        double kosten = 0;

        for(Produkt produkte : produkt){
            kosten += produkte.getPreis();

        }

        return kosten;

    }



}

