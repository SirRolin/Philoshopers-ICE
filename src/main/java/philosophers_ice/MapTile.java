package philosophers_ice;

import ICE.util.HashMapExplorer;
import javafx.scene.image.Image;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MapTile implements Serializable {
    public String name = "";
    public String imagePath = "";
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public ArrayList<EffectCard> effectCards = new ArrayList<EffectCard>();
    public MapTile(String name){
        this.name = name;
    }
    public MapTile(MapTile mt){
        this.name = mt.name;
    }
    public MapTile(HashMap map){
        name = HashMapExplorer.getString(map, "name");
        imagePath = HashMapExplorer.getString(map, "imagePath");
    }

    public Image[] getImage(){
        Image[] output = {null, null};
        String path = !imagePath.equals("") ? imagePath : "Data/gfx/MapTiles/" + name + ".png";
        File file = new File(path);

        //// if it does exist
        if(file.exists()){
            output[0] = new Image(file.toURI().toString());
        } else {
            //// if it doesn't exist
            output[0] = new Image("_NULL_.png");
        }

        path = !imagePath.equals("") ? imagePath : "Data/gfx/Structures/" + name + ".png";
        file = new File(path);

        //// if it does exist
        if(file.exists()){
            output[1] = new Image(file.toURI().toString());
        } else {
            //// if it doesn't exist
            output[1] = new Image("_NULL_.png");
        }
        return output;
    }

}
