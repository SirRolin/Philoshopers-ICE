package philosophers_ice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Effect implements Serializable {
    String name;
    int valueBy;
    public HashMap statChange;

    Effect(String name,int valueBy, HashMap<String ,Integer> statChange){
        this.name = name;
        this.valueBy = valueBy;
        this.statChange = statChange;

    }

    public int statCalculator(String stat){
        if(statChange.containsKey(stat)){

            return (int)statChange.get(stat);
        }
        else{
            return 0;
        }
    }

    public String getName(){
        return name;
    }

    public int getValueBy(){
        return valueBy;
    }

}
