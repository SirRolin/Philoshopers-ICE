import java.util.Random;

public class Melee extends Weapon{
    private String name;
    private int minDamage;
    private int maxDamage;

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
