package philosophers_ice;

import ICE.util.FileInterpreter;
import ICE.util.HashMapExplorer;
import javafx.scene.image.Image;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Currency implements Serializable {
    private static final ArrayList<Currency> listOfUs = new ArrayList<>();
    public static Currency getcurrency(String name){
        for(Currency cur: listOfUs){
            if(cur.name.equals(name)){
                return cur;
            }
        }
        return null;
    }
    //// remember NOT to override any elements
    public static ArrayList<Currency> getRaces(){
        return listOfUs;
    }
    public static void load(){
        if(listOfUs.isEmpty()) {
            for (ArrayList<HashMap<String, Object>> lst : FileInterpreter.parseFolder("Data/common/currencies/")) {
                HashMapExplorer.ListMapToForEach(lst, "currency", (map) -> {listOfUs.add(new Currency(map));});
            }
        }
    }
    public static void reload(){
        listOfUs.clear();
        load();
    }
    public int amount;
    public String description;
    public String name;
    public String imagePath;
    public Currency(HashMap<String,Object> map){
       this.description = (String) map.getOrDefault("description","");
       this.name = (String) map.getOrDefault("name","");
    }

    public Currency(Currency _this, int amount){
        this.amount = amount;
        this.description = _this.description;
        this.name = _this.name;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription(){
        return description;
    }

    public String getName(){
        return name;
    }

    //// not that I expect it to have one
    public Image getImage(){
        String path = imagePath != null ? imagePath : "Data/gfx/currencies/" + name + ".png";
        File file = new File(path);

        //// if it does exist
        if(file.exists()){
            new Image(file.toURI().toString());
        }

        //// if it doesn't exist
        return new Image("_NULL_.png");
    }

}
