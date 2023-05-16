package philosophers_ice;

public class Armour extends Item{
    public String name;
    public String type;
    public int defence;

    public Armour(String name,int defence){
        this.name = name;
        this.defence = defence;
    }
    public int getDefence(){
        return defence;
    }

}
