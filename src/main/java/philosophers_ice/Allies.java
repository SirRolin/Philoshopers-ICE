package philosophers_ice;

import java.io.Serializable;
import java.util.ArrayList;

public class Allies implements Serializable {
    public ArrayList<Effect> buffs;
    public String name;
    
    public String description;

    public Allies(ArrayList<Effect> buffs, String name,String description) {
        this.buffs = buffs;
        this.name = name;
        this.description = description;
    }
    public ArrayList<Effect> getBuffs() {
        return buffs;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
