package philosophers_ice;

import java.io.Serializable;

public class Effect implements Serializable {
    String name;
    int valueBy;
    Effect(String name,int valueBy){
        this.name = name;
        this.valueBy = valueBy;
    }

    public String getName(){
        return name;
    }

    public int getValueBy(){
        return valueBy;
    }

}
