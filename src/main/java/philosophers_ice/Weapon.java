package philosophers_ice;

import ICE.util.HashMapExplorer;
import philosophers_ice.Item;

import java.util.HashMap;
import java.util.Hashtable;

public abstract class Weapon extends Item {
    private boolean isOneHanded;
    private boolean isTwoHanded;
    Weapon(HashMap<String,Object> map){
        name = HashMapExplorer.getString(map,"name");
        isOneHanded = true; //// todo remove when parser knows booleans :P
        isTwoHanded = true; //// todo remove when parser knows booleans :P
    }
    Weapon(boolean isOneHanded,boolean isTwoHanded){
        this.isOneHanded = isOneHanded;
        this.isTwoHanded = isTwoHanded;
    }
    abstract public int getDmg();
    abstract public Boolean getType();
    abstract public String getDmgType();
    public boolean isOneHanded(){
        return isOneHanded;
    }
    public boolean isTwoHanded(){
        return isTwoHanded;
    }
}
