package philosophers_ice;

import ICE.util.HashMapExplorer;
import ICE.util.RngHandler;
import ICE.util.WeightedObject;
import javafx.scene.image.Image;
import kotlin.random.Random;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Enemy implements Serializable {
    private String name;
    private String imagePath;
    private String description;
    private int defence;
    private int hp;
    private int initiative;
    private int damage;
    private ArrayList<Item> loot = new ArrayList<>();


    public Enemy(String name,String imagePath ,String description, int defence,int hp,int initiative ,int damage) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.defence = defence;
        this.hp = hp;
        this.initiative = initiative;
        this.damage = damage;
        this.loot.add(new Melee("Ged",23,53,true,false)); // PLACEHOLDER!!!
        this.loot.add(new Melee("Ged2",23,53,true,false));
    }
    public Enemy(HashMap<String, Object> map){
        name = HashMapExplorer.getString(map,"name");
        imagePath = HashMapExplorer.getString(map,"imagePath");
        description = HashMapExplorer.getString(map,"description");
        defence = (int) HashMapExplorer.getNumber(map,"defence");
        hp = (int) HashMapExplorer.getNumber(map,"hp");
        initiative = (int) HashMapExplorer.getNumber(map,"initiative");
        damage = (int) HashMapExplorer.getNumber(map,"damage");
        ArrayList lst = HashMapExplorer.getList(map, "droplist");
        float totalWeight = 0;
        ArrayList<Object> items = new ArrayList<>();
        for(int ite = 0; ite < lst.size(); ++ite){
            if(lst.get(ite) instanceof WeightedObject chanceItem){
                if(chanceItem.weight.floatValue() > 0f) {
                    totalWeight += (float) chanceItem.weight;
                    items.add(chanceItem);
                }
            } else if(lst.get(ite) instanceof String garenteedItem) {
                items.add(garenteedItem);
            }
        }
        RngHandler.WeightedObjectsToList(totalWeight, items, loot);
        //loot.add(new Melee("Ged",23,53,true,false)); // PLACEHOLDER!!!

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
    public int takeDamage(int damage){
        int actualdamage  = (int) ((double) damage) * 100 / (100 + getDefence());
        this.hp -= actualdamage;
        return actualdamage;
    }
    public int attack(){
        return damage;
    }
    public ArrayList<Item> droppedLoot(){
        return loot;
    }

    public Image getImage(){
        String path = imagePath != null ? imagePath : "Data/gfx/item/" + name + ".png";
        File file = new File(path);

        //// if it does exist
        if(file.exists()){
            new Image(file.toURI().toString());
        }
        //// if it doesn't exist
        return new Image("_NULL_.png");
    }
}
