package philosophers_ice;

import java.io.Serializable;
import java.util.HashMap;

public class Currency implements Serializable {
    public int value;

    public String description;

    public String currencyType;
    public Currency(HashMap<String,Object> map){
       this.description = (String) map.getOrDefault("description","");
       this.currencyType = (String) map.getOrDefault("currencyType","");
    }

    public Currency(Currency _this, int value){
        this.value = value;
        this.description = _this.getDescription();
        this.currencyType = _this.getCurrencyType();
    }

    public int getValue() {
        return value;
    }

    public String getDescription(){
        return description;
    }

    public String getCurrencyType(){
        return currencyType;
    }

}
