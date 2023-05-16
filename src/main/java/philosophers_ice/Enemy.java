package philosophers_ice;

import java.io.Serializable;

public class Enemy implements Serializable {

    private String name;

    private String imagePath;

    private String description;

    private int defence;

    private int initiative;

    private int damage;

    public Enemy(String name,String imagePath ,String description, int defence,int initiative ,int damage) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.defence = defence;
        this.initiative = initiative;
        this.damage = damage;
    }

    public String getName(){
        return name;
    }

    public int getInitiative(){return initiative;}

    public String getImagePath(){
        return imagePath;
    }

    public String getDescription(){
        return description;
    }

    public int getDefence(){
        return defence;
    }

    public int getDamage(){
        return damage;
    }


}
