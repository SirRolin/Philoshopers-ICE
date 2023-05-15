package philosophers_ice;

import philosophers_ice.Item;

public abstract class Weapon extends Item {
    private boolean isOneHanded;
    private boolean isTwoHanded;
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
