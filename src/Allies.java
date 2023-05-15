import java.util.ArrayList;

public class Allies {
    public ArrayList<Effect> buffs;
    public String name;

    public Allies(ArrayList<Effect> buffs, String name) {
        this.buffs = buffs;
        this.name = name;
    }
    public ArrayList<Effect> getBuffs() {
        return buffs;
    }
    public String getName() {
        return name;
    }

}
