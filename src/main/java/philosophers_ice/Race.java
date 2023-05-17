package philosophers_ice;

import ICE.util.HashMapExplorer;

import java.util.HashMap;

public class Race {
    public String name;
    private String imagePath;

    public Race(HashMap map){
        name = HashMapExplorer.getString(map,"name");
        imagePath = HashMapExplorer.getString(map, "imagePath");
    }
}
