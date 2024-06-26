
public abstract class Mensch {
    private String name;
    private int alter;

    public Mensch(String name, int alter) {
        this.name = name;
        this.alter = alter;
    }

    public String getName(){
        return this.name;
    }

    public int getAlter(){
        return this.alter;
    }
}

