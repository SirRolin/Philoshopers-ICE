import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    private Weapon equippedWeaponMainHand;
    private Weapon equippedWeaponOffhand;
    private Item equippedArmour;
    private Accessory equippedAccessory;
    private int maxSize = 30;
    Player p1;
    public Inventory(){

    }
    public Inventory(Player p1){
    this.p1 = p1;
    }
    public ArrayList<Item> getItems(){
        return items;
    }
    public void addToItems(Item item){
        items.add(item);
    }
    public int getDamage(){
        int dmg = equippedWeaponMainHand.getDmg();
        if(!equippedWeaponOffhand.getType()){
            dmg += equippedWeaponOffhand.getDmg();
        }
        return dmg;
    }
    public int getDefence(){
        return 0;
    }
    public void equipItem(Item item){
        if(item instanceof Weapon){
            this.equippedWeaponMainHand = ((Weapon) item);
        }
    }
}
