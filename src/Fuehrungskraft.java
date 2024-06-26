

public class Fuehrungskraft extends Mensch{

    Unternehmen unternehmen;
    private int unternehmenID;

    public Fuehrungskraft(String name, int alter, Unternehmen unternehmen) {
        super(name, alter);
        this.unternehmen = unternehmen;
        unternehmen.addFuehrungskraft(this);
    }

    public Fuehrungskraft(String name, int alter, Unternehmen unternehmen, int unternehmenID) {
        super(name, alter);
        this.unternehmen = unternehmen;
        unternehmen.addFuehrungskraft(this);
        this.unternehmenID = unternehmenID;
    }

    public Fuehrungskraft(String name, int alter, int unternehmenID) {
        super(name, alter);
        this.unternehmen = unternehmen;
        //unternehmen.addFuehrungskraft(this); Muss umgeschrieben werden
        this.unternehmenID = unternehmenID;
    }


    public String toString() {
        return "Mitarbeiter{" +
                "name='" + getName() +
                ", alter=" + getAlter() +
                ", Unternehmen= " + unternehmen.getName() +
                ", unternehmenID=" + getUnternehmenID() +
                '}';
    }

    public int getUnternehmenID() {
        return this.unternehmenID;
    }

    public void MitarbeiterEinstellen(Unternehmen unternehmen, Mitarbeiter mitarbeiter){

        if(unternehmen.checkObMaExistiert(mitarbeiter)){
            System.out.println("Dieser Mitarbeiter arbeitet bereits bei " + unternehmen.getName() + "");
        }else{
            unternehmen.addMitarbeiter(mitarbeiter);
            mitarbeiter.setgehoertZuEinemUnternehmen(true);
        }


    }

    public void MitarbeiterEinstellen(Unternehmen unternehmen, MitarbeiterMarketing mitarbeiter){
        if(unternehmen.checkObMaExistiert(mitarbeiter)){
            System.out.println("Dieser Mitarbeiter arbeitet bereits bei " + unternehmen.getName() + "");
        }else{
            unternehmen.addMitarbeiter(mitarbeiter);
            mitarbeiter.setgehoertZuEinemUnternehmen(true);
        }
    }

    public void MitarbeiterBearbeiten(MitarbeiterMarketing mitarbeiter, String neueAbteilung){
        mitarbeiter.setAbteilung(neueAbteilung);
    }

    public void MitarbeiterBearbeiten(Mitarbeiter mitarbeiter, String neueAbteilung){
        mitarbeiter.setAbteilung(neueAbteilung);
    }

    public void MitarbeiterEntlassen(Mitarbeiter mitarbeiter, Unternehmen unternehmen){
        unternehmen.removeMitarbeiter(mitarbeiter);
        mitarbeiter.setgehoertZuEinemUnternehmen(false);
    }

    public void MitarbeiterEntlassen(MitarbeiterMarketing mitarbeiter, Unternehmen unternehmen){
        unternehmen.removeMitarbeiter(mitarbeiter);
        mitarbeiter.setgehoertZuEinemUnternehmen(false);
    }

}

