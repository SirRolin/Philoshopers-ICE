import java.util.Random;

public class Melee extends Weapon{
    private String name;
    private int minDamage;
    private int maxDamage;
    private boolean isTwoHanded;

    Melee(String name, int minDamage,int maxDamage,boolean isTwoHanded){
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.isTwoHanded = isTwoHanded;
    }
    @Override
    public int getDmg() {
        Random r1 = new Random();
        return r1.nextInt(maxDamage-minDamage)+minDamage;
    }
    @Override
    public Boolean getType() {
        return isTwoHanded;
    }
    @Override
    public String getDmgType() {
        return "Slash";
    }
}
