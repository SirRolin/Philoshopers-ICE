package philosophers_ice;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Item implements Serializable {
    public String name;
    public String description;
    public ArrayList<Effect> statChanges;
    public String getName(){
        return this.name;
    }
}
