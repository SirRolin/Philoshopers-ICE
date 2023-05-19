package philosophers_ice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory implements Serializable {
    private ArrayList<Item> items = new ArrayList<>();
    private Weapon equippedWeaponMainHand = null;
    private Weapon equippedWeaponOffhand = null;
    private Armour equippedArmour = null;
    private Accessory equippedAccessory = null;
    private final ArrayList<Currency> currencies = new ArrayList<>();
    private int maxSize = 30;
    Player p1;

    public Inventory() {
    }

    public Inventory(Player p1) {
        this.p1 = p1;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addToItems(Item item) {
        items.add(item);
    }

    public int getDamage() {
        //todo implement mainhand and offhand correctly
        int dmg = 1;
        if(equippedWeaponMainHand != null) {
            dmg += equippedWeaponMainHand.getDmg();
            if (!equippedWeaponOffhand.getType()) {
                dmg += equippedWeaponOffhand.getDmg();
            }
        }
        return dmg;
    }
    public Number getEffectModifiers(String stat){
        return 0;
       // todo WORK IN PROGRESS!! part of nice to have
    }
    public Weapon getEquippedWeaponMainHand() {
        return equippedWeaponMainHand;
    }

    public Weapon getEquippedWeaponOffhand() {
        return equippedWeaponOffhand;
    }

    public Armour getEquippedArmour() {
        return equippedArmour;
    }

    public Accessory getEquippedAccessory() {
        return equippedAccessory;
    }

    public int getDefence() {
        int def;
        if(equippedArmour == null){
            def = 0;
            return def;
        }
        def = equippedArmour.getDefence();
        return def;
    }

    public void equipItem(Item item) {
        if (item instanceof Weapon) {
            equipWeapon((Weapon) item);
        }
        if (item instanceof Armour) {
            equipArmour((Armour) item);
        }
        if(item instanceof Accessory){
            
        }
    }

    private void equipArmour(Armour item) {
        if (this.equippedArmour == null)
            this.equippedArmour = item;
            items.remove(item);
        if (this.equippedArmour != null) {
            Scanner s1 = new Scanner(System.in);
            System.out.println("You already got " + this.equippedArmour.name + " equipped, do you want to equip" + item.name + " instead? Y/N");
            String input = s1.nextLine();
            if (input.equalsIgnoreCase("y")) {
                items.add(this.equippedArmour);
                this.equippedArmour = item;
                items.remove(item);
            } else if (input.equalsIgnoreCase("n")) {
                System.out.println(item.name + "Was not equiped");
            } else {
                System.out.println("did not recognize your input");
                equipArmour(item);
            }
        }
    }

    private void equipWeapon(Weapon item) {
        //todo implement mainhand and offhand correctly (you should be able to unequip)
        if (item.isOneHanded()) {
            equipOneHanded(item);
        } else if (item.isTwoHanded()) {
            equipTwoHanded(item);
        } else {
            System.out.println("HILFE, weapon is weird");
        }

    }

    private void equipOneHanded(Weapon item) {
        if (this.equippedWeaponMainHand == null) {
            System.out.println("You equipped " + item.getName() + "in your mainhand.");
            this.equippedWeaponMainHand = ((Weapon) item);
            items.remove(item);
        }
        Scanner s1 = new Scanner(System.in);
        System.out.println("You already got " + this.equippedWeaponMainHand.name + " equipped in mainhand, do you want to equip" + item.name + " instead? Y/N");
        String input = s1.nextLine();
        if (input.equalsIgnoreCase("y")) {
            System.out.println("You equipped " + item.getName() + "in your mainhand, instead of" + this.equippedWeaponMainHand.getName());
            items.add(this.equippedWeaponMainHand);
            this.equippedWeaponMainHand = ((Weapon) item);
            items.remove(item);
        } else if (input.equalsIgnoreCase("n") && equippedWeaponOffhand == null) {
            System.out.println("do you want to equip" + item.name + " in offhand? Y/N");
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
        } else if (input.equalsIgnoreCase("n") && equippedWeaponOffhand != null) {
            System.out.println("You already have " + equippedWeaponOffhand.getName() + "do you want to equiped in your offhand, do you want to equip" + item.name + " instead? Y/N");
            String input2 = s1.nextLine();
            if (input2.equalsIgnoreCase("y")) {
                System.out.println("You equipped " + item.getName() + "in your offhand.");
                items.add(this.equippedWeaponMainHand);
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


    private void equipTwoHanded(Weapon item) {
        if (this.equippedWeaponMainHand == null) {
            this.equippedWeaponMainHand = ((Weapon) item);
            items.remove(item);
        }
        if (this.equippedWeaponMainHand != null) {
            Scanner s1 = new Scanner(System.in);
            System.out.println("You already got " + this.equippedWeaponMainHand.name + " equipped in mainhand, do you want to equip" + item.name + " instead? Y/N");
            String input = s1.nextLine();
            if (input.equalsIgnoreCase("y")) {
                items.add(this.equippedWeaponMainHand);
                this.equippedWeaponMainHand = ((Weapon) item);
                items.remove(item);
            } else if (input.equalsIgnoreCase("n")) {
                System.out.println("You did not equip" + item.getName());
            }
        } else {
            System.out.println("did not recognize your input");
            equipOneHanded(item);
        }
    }
    public Item getItem(String nameOfItem){
        for(Item item: items){
            if(item.name.equals(nameOfItem)){
                return item;
            }
        }
        return null;
    }

    public int getMaxSize() {
        return maxSize + (p1.str * 2);
    }

    public Currency getCurrency(String name){
        for(Currency c: currencies){
            if(c.name.equals(name)){
                return c;
            }
        }
        return null;
    }

    public void addCurrency(String name, int amount){
        Currency cur = getCurrency(name);
        if(cur == null){
            cur = Currency.getcurrency(name);
        }
        addCurrency(cur, amount);
    }
    public void addCurrency(Currency cur, int amount){
        if(cur != null){
            Currency cur2 = getCurrency(cur.name);
            if(cur2 != null){
                cur2.amount += amount;
            } else {
                currencies.add(new Currency(cur, amount));
            }
        }
    }

}



