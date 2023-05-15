package philosophers_ice;

import java.util.ArrayList;

public class Boon {
    public ArrayList<Effect> buffs;
    public String name;
    public String desc;
    public Boon(ArrayList<Effect> buffs, String name, String desc) {
        this.buffs = buffs;
        this.name = name;
        this.desc = desc;
    }

    public ArrayList<Effect> getBuffs() {
        return buffs;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
