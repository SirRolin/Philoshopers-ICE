package philosophers_ice;

import ICE.util.HashMapExplorer;

import java.util.HashMap;

public class Armour extends Item{
    public String type;
    public int defence;

    Armour(HashMap<String,Object> map){
        name = HashMapExplorer.getString(map,"name");
        defence = (int) HashMapExplorer.getNumber(map, "defence");
    }

    public Armour(String name,int defence){
        this.name = name;
        this.defence = defence;
    }
    public int getDefence(){
        return defence;
    }

}
