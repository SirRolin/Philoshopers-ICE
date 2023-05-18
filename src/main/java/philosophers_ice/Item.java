package philosophers_ice;

import javafx.scene.image.Image;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;

public abstract class Item implements Serializable {
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
