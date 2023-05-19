package philosophers_ice;

import java.io.Serializable;
import java.util.ArrayList;

public class EffectCard implements Serializable {
    public ArrayList<Effect> statChanges;
    public String name;
    public String desc;
    public EffectCard(ArrayList<Effect> statChanges, String name, String desc) {
        this.statChanges = statChanges;
        this.name = name;
        this.desc = desc;
    }

    public ArrayList<Effect> getStatChanges() {
        return statChanges;
    }

    public void addBuffs(){

    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
