public class Enemy {

    private String name;

    private String imagePath;

    private String description;

    private int defence;

    private int damage;

    public Enemy(String name,String imagePath ,String description, int defence, int damage) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.defence = defence;
        this.damage = damage;
    }

    public String getName(){
        return name;
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

    public int getDamage(){
        return damage;
    }


}
