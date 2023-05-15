package philosophers_ice;

public class Effect {
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
