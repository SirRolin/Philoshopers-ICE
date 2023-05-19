package philosophers_ice;

import ICE.util.FileInterpreter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Effect implements Serializable {
    public String name;
    public int valueBy;
    public HashMap statChange;

    public Effect(String name,int valueBy, HashMap<String ,Integer> statChange){
        this.name = name;
        this.valueBy = valueBy;
        this.statChange = statChange;
    }
    private static final ArrayList<Effect> listOfUs = new ArrayList<Effect>();
    public static Effect getItem(String nameOfItem){
        for(Effect effect: listOfUs){
            if(effect.name == nameOfItem){
                return effect;
            }
        }
        return null;
    }
    public static void load(){
        // todo add effects to common
        if(listOfUs.isEmpty()) {
            for (ArrayList<HashMap<String, Object>> lst : FileInterpreter.parseFolder("Data/common/effects")) {
                for (HashMap<String, Object> s : lst
                ) {
                    if (s.containsKey("effect")) {
                        //listOfUs.add(new Effect(s));
                    }
                }
            }
        }
    }
    public static void reload(){
        listOfUs.clear();
        load();
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
