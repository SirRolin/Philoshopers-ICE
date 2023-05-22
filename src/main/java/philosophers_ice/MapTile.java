package philosophers_ice;

import ICE.util.HashMapExplorer;
import ICE.util.RngHandler;
import javafx.scene.image.Image;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MapTile implements Serializable {
    public String name = "";
    public String structure;
    public String imagePath = "";
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public ArrayList<EffectCard> effectCards = new ArrayList<EffectCard>();
    public MapTile(String name){
        this(name,null);
    }
    public MapTile(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
    }
    public MapTile(MapTile mt){
        this(mt.name, mt.imagePath);
    }
    public MapTile(HashMap<?,?> map){
        name = HashMapExplorer.getString(map, "name");
        imagePath = HashMapExplorer.getString(map, "imagePath");
    }

    public Image[] getImage(){
        Image[] output = {null, null};
        String path = (imagePath != null) && !imagePath.equals("") ? imagePath : "Data/gfx/map_tiles/" + name + ".png";
        File file = new File(path);

        //// if it does exist
        if(file.exists()){
            output[0] = new Image(file.toURI().toString());
        } else {
            //// if it doesn't exist
            output[0] = new Image("_NULL_.png");
        }

        path = "Data/gfx/structures/" + structure + ".png";
        file = new File(path);

        //// if it does exist
        if(file.exists()){
            output[1] = new Image(file.toURI().toString());
        } else {
            //// if it doesn't exist
            output[1] = null;
        }
        return output;
    }

    public void visit(Player p1){
        // todo pre comabt events
        // todo combat
        if(!enemies.isEmpty()){
            CombatScene cs = new CombatScene(p1, enemies);
            cs.startCombat();
        }
        // todo post combat events (if alive)
    }

    public void passTime(){
        // todo chance for enemies to spawn
        // PS. Use the RngHandler
    }

}
