package philosophers_ice;

import ICE.util.HashMapExplorer;

import java.util.HashMap;
import java.util.Random;

public class Melee extends Weapon {
    private int minDamage;
    private int maxDamage;


    Melee(HashMap<String, Object> map) {
        super(map);
        minDamage = (int) HashMapExplorer.getNumber(map, "damage.min");
        maxDamage = (int) HashMapExplorer.getNumber(map, "damage.max");
    }
    Melee(String name, int minDamage,int maxDamage,boolean isOneHanded, boolean isTwoHanded){
        super(isOneHanded,isTwoHanded);
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }
    @Override
    public int getDmg() {
        Random r1 = new Random();
        return r1.nextInt(maxDamage-minDamage)+minDamage;
    }
    @Override
    public Boolean getType() {
        return false;
    }

    @Override
    public String getDmgType() {
        return "Slash";
    }
}
