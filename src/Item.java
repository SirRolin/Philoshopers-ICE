import java.util.ArrayList;

public abstract class Item {
    public String name;
    public String description;
    public ArrayList<Effect> buffs;
    public String getName(){
        return this.name;
    }
}
