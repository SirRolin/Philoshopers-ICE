package philosophers_ice;

import java.io.Serializable;
import java.util.ArrayList;

public class Enemy implements Serializable {

    private String name;

    private String imagePath;

    private String description;

    private int defence;
    private int hp;

    private int initiative;

    private int damage;
    private ArrayList<Item> loot;


    public Enemy(String name,String imagePath ,String description, int defence,int hp,int initiative ,int damage) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.defence = defence;
        this.hp = hp;
        this.initiative = initiative;
        this.damage = damage;
        this.loot.add(new Melee("Ged",23,53,true,false)); // PLACEHOLDER!!!
    }
    public String getName(){
        return name;
    }
    public int getInitiative(){return initiative;}
    public void updateInitiative(int input){
        this.initiative += input;
    }
    public String getImagePath(){
        return imagePath;
    }
    public String getDescription(){
        return description;
    }
    public int getDefence(){
        return defence;
    }
    public int getHp(){
        return hp;
    }
    public void updateHP(int input){
        this.hp += input;
    }
    public int attack(){
        return damage;
    }
    public ArrayList<Item> droppedLoot(){
        return loot;
    }
}
