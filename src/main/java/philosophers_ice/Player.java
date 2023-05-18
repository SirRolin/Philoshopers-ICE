package philosophers_ice;

import javafx.scene.image.Image;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    public String name;
    public Race race;
    public int str;
    public int agi;
    public int con;
    public int wits;
    public int initiative;
    public int willPower;
    public int magi;
    private int hp;
    private int mp;
    private ArrayList<EffectCard> effectCards;
    private Inventory inventory;


    public Player(String name){
        this.name = name;
    }
    public Player(String name, Race race, int str, int agi, int con,int wits,int willPower,int magi){
        this.name = name;
        this.race = race;
        this.str = str;
        this.agi = agi;
        this.con = con;
        this.wits = wits;
        this.willPower = willPower;
        this.magi = magi;
        this.hp = getMaxHP();
        this.mp = getMaxMP();
        this.initiative = wits*2+3;
        inventory = new Inventory();
    }
    public int getMaxHP(){
        return con*3+50;
    }
    public int getMaxMP(){
        return willPower*2+5;
    }
    public int getSpeed(){
        return agi*2+5;
    }
    public int getInitiative(){
        return initiative;
    }
    public int getSpellBuffProc(){
        return magi*2+30;
    }
    public void increaseHP(int hp){
        this.hp += hp;
    }
    public int takeDamage(int damage){
        int actualdamage  = (int) ((double) damage) * 100 / (100 + getDefence());
        this.hp -= actualdamage;
        return actualdamage;
    }

    private int getDefence() {
        return inventory.getDefence();
    }

    public void increaseMP(int mp){
        this.mp += mp;
    }
    public int getHP(){
        return this.hp;
    }
    public int getMP(){
        return this.mp;
    }
    public void recover(){
        this.hp += (con+(str/2)+willPower)+ 20;
        if(this.hp > getMaxHP()){
            this.hp = getMaxHP();
        }
    }
    public int attack(){
        int damage = inventory.getDamage() ;
        inventory.getEffectModifiers(); // WORK IN PROGESS !!!! Part of nice to have
        if(inventory.getEquippedWeaponMainHand() instanceof Melee){
           damage *= str/2;
        }
        if(inventory.getEquippedWeaponMainHand() instanceof Ranged){
            damage *= agi/2;
        }
        return damage;
    }
    public void getLoot(ArrayList<Item> items){
        for (Item i: items) {
            this.inventory.addToItems(i);
        }
    }

    public Image getImage(){
        if(race!=null){
            return race.getImage();
        }
        return new Image("_NULL_.png");
    }


}
