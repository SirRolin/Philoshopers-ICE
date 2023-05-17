package philosophers_ice;

import ICE.util.HashMapExplorer;

import java.io.Serializable;
import java.util.HashMap;

public class Race implements Serializable {
    public String name;
    private String imagePath;

    public Race(HashMap map){
        name = HashMapExplorer.getString(map,"name");
        imagePath = HashMapExplorer.getString(map, "imagePath");
    }
    //// debugging and temporary
    public Race(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
    }
}
