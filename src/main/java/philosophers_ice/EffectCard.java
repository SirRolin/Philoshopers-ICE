package philosophers_ice;

import ICE.util.HashMapExplorer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class EffectCard implements Serializable {
    public ArrayList<Effect> statChanges;
    public String name;
    public String desc;
    public String imagePath;
    public EffectCard(ArrayList<Effect> statChanges, String name, String desc) {
        this.statChanges = statChanges;
        this.name = name;
        this.desc = desc;
    }

    public EffectCard(HashMap<?,?> map){
        name = HashMapExplorer.getString(map,"name");
        imagePath = HashMapExplorer.getString(map,"imagePath");
    }

    public ArrayList<Effect> getStatChanges() {
        return statChanges;
    }

    void addBuffs(){

    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
