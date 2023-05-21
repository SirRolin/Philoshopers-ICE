package philosophers_ice;

import ICE.util.FileInterpreter;
import ICE.util.HashMapExplorer;
import ICE.util.RngHandler;
import ICE.util.WeightedObject;
import javafx.scene.image.Image;
import kotlin.random.Random;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

import static javafx.scene.input.KeyCode.T;

public class Enemy implements Serializable {
    private String name;
    private String imagePath;
    private String description;
    private int defence;
    private int hp;
    private int initiative;
    private int maxInitiative;
    private int damage;
    private ArrayList<String> loot = new ArrayList<>();
    private ArrayList<WeightedObject> chanceLoot = new ArrayList<>();
    private ArrayList<Currency> currencies = new ArrayList<>();
    private static final HashMap<String, Enemy> listOfUs = new HashMap<String, Enemy> ();

    public static Enemy getEnemy(String nameOfEnemy) {
        load();
        return listOfUs.get(nameOfEnemy);
    }

    public static void load() {
        if (listOfUs.isEmpty()) {
            for (ArrayList<HashMap<String, Object>> lst : FileInterpreter.parseFolder("Data/common/enemies/")) {
                HashMapExplorer.ListMapToForEach(lst, (s, map) -> {
                    listOfUs.put(s, new Enemy((HashMap<String, Object>) map));
                });
            }
        }
    }

    public static void reload() {
        listOfUs.clear();
        load();
    }

    public Enemy(String name) {
        this(Enemy.getEnemy(name));
    }
    public Enemy(Enemy enemy) {
        this.name = enemy.name;
        this.imagePath = enemy.imagePath;
        this.description = enemy.description;
        this.defence = enemy.defence;
        this.hp = enemy.hp;
        this.maxInitiative = enemy.maxInitiative;
        this.damage = enemy.damage;
        this.loot = enemy.loot;
        this.chanceLoot = enemy.chanceLoot;
        this.currencies = enemy.currencies;

    }

    public Enemy(String name, String imagePath, String description, int defence, int hp, int initiative, int damage) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.defence = defence;
        this.hp = hp;
        this.initiative = initiative;
        this.maxInitiative = initiative;
        this.damage = damage;
        this.loot.add("cheese");
        //this.loot.add(new Melee("Ged", 23, 53, true, false)); // PLACEHOLDER!!!
    }

    public Enemy(HashMap<String, Object> map) {
        name = HashMapExplorer.getString(map, "name");
        imagePath = HashMapExplorer.getString(map, "imagePath");
        description = HashMapExplorer.getString(map, "description");
        defence = HashMapExplorer.getNumber(map, "defence").intValue();
        hp = HashMapExplorer.getNumber(map, "health").intValue();
        initiative = HashMapExplorer.getNumber(map, "initiative").intValue();
        maxInitiative = initiative;
        damage = HashMapExplorer.getNumber(map, "damage").intValue();
        ArrayList<Object> lst = HashMapExplorer.getList(map, "droplist.list");
        for (int ite = 0; ite < lst.size(); ++ite) {
            if (lst.get(ite) instanceof WeightedObject chanceItem) {
                if (chanceItem.weight.floatValue() > 0f) {
                    chanceLoot.add(chanceItem);
                }
            } else if (lst.get(ite) instanceof String garenteedItem) {
                loot.add(garenteedItem);
            }
        }
        RngHandler.WeightedObjectsToList(chanceLoot, loot);

    }

    public String getName() {
        return name;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getMaxInitiative() {
        return maxInitiative;
    }

    public void updateInitiative(int input) {
        this.initiative += input;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }

    public int getDefence() {
        return defence;
    }

    public int getHp() {
        return hp;
    }

    public void updateHP(int input) {
        this.hp += input;
    }

    public int takeDamage(int damage) {
        int actualdamage = (int) ((double) damage) * 100 / (100 + getDefence());
        this.hp -= actualdamage;
        return actualdamage;
    }

    public int attack() {
        return damage;
    }

    public ArrayList<Item> droppedLoot() {
        //// Declare output Items
        ArrayList<Item> items = new ArrayList<>();

        //// Add a random item from chancelist
        RngHandler.WeightedObjectsToList(chanceLoot, items);

        //// add guaranteed items
        loot.forEach((String s) -> {
            Item item = Item.getItem((s));
            if(item != null) {
                items.add(item);
            }
        });

        //// return
        return items;
    }

    public Image getImage() {
        String path = imagePath != null ? imagePath : "Data/gfx/item/" + name + ".png";
        File file = new File(path);

        //// if it does exist
        if (file.exists()) {
            new Image(file.toURI().toString());
        }
        //// if it doesn't exist
        return new Image("_NULL_.png");
    }
}
