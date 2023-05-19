package philosophers_ice;

import ICE.util.FileInterpreter;
import javafx.scene.image.Image;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Item implements Serializable {
    private static final ArrayList<Item> listOfUs = new ArrayList<Item>();
    public static Item getItem(String nameOfItem){
        for(Item item: listOfUs){
            if(item.name.equals(nameOfItem)){
                return item;
            }
        }
        return null;
    }
    public static void load(){
        if(listOfUs.isEmpty()) {
            for (HashMap<String, Object> s : FileInterpreter.parseFolder("Data/common/items/")) {
                if (s.containsKey("melee_weapon")) {
                    listOfUs.add(new Melee(s));
                }
                if(s.containsKey("ranged_weapon")){
                    listOfUs.add(new Ranged(s));
                }
                if(s.containsKey("armour")){
                    listOfUs.add(new Armour(s));
                }
            }
        }
    }
    public static void reload(){
        listOfUs.clear();
        load();
    }
//
//    public Item(Item item){
//        name = item.name;
//        description = item.name;
//        imagePath = item.imagePath;
//        statChanges = new ArrayList<Effect>();
//    }

    public String name;
    public String description;
    public ArrayList<Effect> statChanges;
    public String getName(){
        return this.name;
    }
    public String imagePath;

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
