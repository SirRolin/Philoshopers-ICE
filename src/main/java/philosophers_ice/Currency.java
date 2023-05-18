package philosophers_ice;

import javafx.scene.image.Image;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

public class Currency implements Serializable {
    public int amount;
    public String description;
    public String name;
    public String imagePath;
    public Currency(HashMap<String,Object> map){
       this.description = (String) map.getOrDefault("description","");
       this.name = (String) map.getOrDefault("currencyType","");
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
