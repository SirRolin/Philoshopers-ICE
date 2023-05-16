package philosophers_ice;

import java.util.ArrayList;

public class Enchantment {

    public ArrayList<Effect> stats;
    public String prefix;
    public String suffix;

    public Enchantment(ArrayList<Effect> stats,String prefix,String suffix){
       this.stats = new ArrayList<>();
       this.prefix = prefix;
       this.suffix = suffix;
    }

    public String getPrefix(){
        return prefix;
    }

    public String getSuffix(){
        return suffix;
    }

}
