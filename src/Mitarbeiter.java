

public class Mitarbeiter extends Mensch{

    String abteilung;
    boolean gehoertZuEinemUnternehmen = false;

    private int unternehmenID;

    public Mitarbeiter(String name, int alter, String abteilung) {
        super(name, alter);
        this.abteilung = abteilung;
    }



    public Mitarbeiter(String name, int alter, String abteilung, int unternehmenID) {
        super(name, alter);
        this.abteilung = abteilung;
        this.unternehmenID = unternehmenID;
        this.gehoertZuEinemUnternehmen = true;
    }

    public Mitarbeiter(String name, int alter, String abteilung, Unternehmen unternehmen, int unternehmenID) {
        super(name, alter);
        this.abteilung = abteilung;
        unternehmen.addMitarbeiter(this);
        this.unternehmenID = unternehmenID;
        this.gehoertZuEinemUnternehmen = true;
    }

    public String toString() {
        return "Mitarbeiter{" +
                "name='" + getName() +
                ", alter=" + getAlter() +
                ", abteilung='" + getAbteilung() +
                ", unternehmenID=" + getUnternehmenID() +
                '}';
    }
    public int getUnternehmenID(){
        return  this.unternehmenID;
    }


    public void setgehoertZuEinemUnternehmen(boolean bool){

        this.gehoertZuEinemUnternehmen = bool;

    }

    public boolean getgehoertZuEinemUnternehmen(){

        return this.gehoertZuEinemUnternehmen;
    }

    public void produktVerkaufen(Kunde kunde, Produkt produkt, Unternehmen unternehmen){

        if(this.gehoertZuEinemUnternehmen){
            kunde.addVertrag(produkt);
        }else{
            System.out.println("Der Mitareiter gehört zu keinem Unternehmen und darf niemanden was verkaufen");
        }


    }

    public void KundeKündigen(Unternehmen unternehmen, Kunde kunde){

        unternehmen.removeKunde(kunde);
    }

    public void setAbteilung(String abteilung){
        this.abteilung = abteilung;
    }

    public String getAbteilung(){
        return this.abteilung;
    }

}

