import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private ArrayList<Item> items;
    private Weapon equippedWeaponMainHand;
    private Weapon equippedWeaponOffhand;
    private Armour equippedArmour;
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
            equipWeapon((Weapon) item);
        }
        if(item instanceof Armour){
            equipArmour((Armour) item);
        }
    }
    private void equipArmour(Armour item){
        if(this.equippedArmour == null)
            this.equippedArmour = item;
        if(this.equippedArmour != null){
            Scanner s1 = new Scanner(System.in);
            System.out.println("You already got "+this.equippedArmour.name+" equipped, do you want to equip"+item.name+" instead? Y/N");
            String input = s1.nextLine();
            if(input.equalsIgnoreCase("y")){
                this.equippedArmour = item;
            }else if(input.equalsIgnoreCase("n")){
                System.out.println(item.name+"Was not equiped");
            }else{
                System.out.println("did not recognize your input");
                equipArmour(item);
            }
        }
    }
    private void equipWeapon(Weapon item) {
        if (item.isOneHanded()) {
            equipOneHanded(item);
        }else if(item.isTwoHanded()){
            equipTwoHanded(item);
        }else{
            System.out.println("HILFE, weapon is weird");
        }

    }
    public void equipOneHanded(Weapon item){
        if (this.equippedWeaponMainHand == null) {
            System.out.println("You equipped " + item.getName() + "in your mainhand.");
            this.equippedWeaponMainHand = ((Weapon) item);
        }
        if (this.equippedWeaponMainHand != null) {
            Scanner s1 = new Scanner(System.in);
            System.out.println("You already got " + this.equippedWeaponMainHand.name + " equipped in mainhand, do you want to equip" + item.name + " instead? Y/N");
            String input = s1.nextLine();
            if (input.equalsIgnoreCase("y")) {
                System.out.println("You equipped " + item.getName() + "in your mainhand, instead of"+this.equippedWeaponMainHand.getName());
                this.equippedWeaponMainHand = ((Weapon) item);
            } else if (input.equalsIgnoreCase("n") && equippedWeaponOffhand == null) {
                System.out.println("do you want to equip" + item.name + " in offhand? Y/N");
                String input2 = s1.nextLine();
                if (input2.equalsIgnoreCase("y")) {
                    System.out.println("You equipped " + item.getName() + "in your offhand.");
                    this.equippedWeaponOffhand = ((Weapon) item);
                    items.remove(item);
                }else if(input2.equalsIgnoreCase("n")){
                    System.out.println("You did not equip"+item.getName());
                }else {
                    System.out.println("did not recognize your input");
                    equipOneHanded(item);
                }
            }else if(input.equalsIgnoreCase("n") && equippedWeaponOffhand != null) {
                System.out.println("You already have "+equippedWeaponOffhand.getName()+"do you want to equiped in your offhand, do you want to equip"+ item.name + " instead? Y/N");
                String input2 = s1.nextLine();
                if (input2.equalsIgnoreCase("y")) {
                    System.out.println("You equipped " + item.getName() + "in your offhand.");
                    this.equippedWeaponOffhand = ((Weapon) item);
                    items.remove(item);
                } else if (input2.equalsIgnoreCase("n")) {
                    System.out.println("You did not equip" + item.getName());
                } else {
                    System.out.println("did not recognize your input");
                    equipOneHanded(item);
                }
            }
        }
    }
    public void equipTwoHanded(Weapon item){
        if (this.equippedWeaponMainHand == null) {
            this.equippedWeaponMainHand = ((Weapon) item);
        }
        if (this.equippedWeaponMainHand != null) {
            Scanner s1 = new Scanner(System.in);
            System.out.println("You already got " + this.equippedWeaponMainHand.name + " equipped in mainhand, do you want to equip" + item.name + " instead? Y/N");
            String input = s1.nextLine();
            if (input.equalsIgnoreCase("y")) {
                this.equippedWeaponOffhand = ((Weapon) item);
            }else if(input.equalsIgnoreCase("n")){
                System.out.println("You did not equip"+item.getName());
            }
            }else{
                System.out.println("did not recognize your input");
                equipOneHanded(item);
            }
        }
}



