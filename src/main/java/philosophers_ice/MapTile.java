package philosophers_ice;

import javafx.scene.image.Image;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class MapTile implements Serializable {

    public String name = "";

    public String imagePath = "";

    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public ArrayList<EffectCard> effectCards = new ArrayList<EffectCard>();

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
