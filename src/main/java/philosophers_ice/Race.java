package philosophers_ice;

import ICE.util.ErrorHandler;
import ICE.util.FileInterpreter;
import ICE.util.HashMapExplorer;
import javafx.scene.image.Image;



import java.util.HashMap;




import java.io.File;

import java.io.Serializable;
import java.util.ArrayList;

public class Race implements Serializable {

    private static final String folderName = "races";

    private static final ArrayList<Race> listOfUs = new ArrayList<>();

    public static Race getRace(String nameOfRace) {
        for (Race race : listOfUs) {
            if (race.name.equals(nameOfRace)) {
                return race;
            }
        }
        return null;
    }

    //// remember NOT to override any elements
    public static ArrayList<Race> getRaces() {
        return listOfUs;
    }

    public static void load() {
        if (listOfUs.isEmpty()) {
            for (ArrayList<HashMap<String, Object>> lst : FileInterpreter.parseFolder("Data/common/" + folderName + "/")) {
                HashMapExplorer.ListMapToforEach(lst, "melee_weapon", (key, map) -> {
                    listOfUs.add(new Race(map, key));
                });
            }
        }
    }

    public static void reload() {
        listOfUs.clear();
        load();
    }


    public static int getIndexOf(ArrayList<Race> list, String name) {
        int i = 0;
        if (list != null) {
            for (Race r : list) {
                if (r.name.equals(name)) {
                    return i;
                }
                ++i;
            }
            ErrorHandler.handleError(new Exception("missing " + name + " in race list!"));
        } else {
            ErrorHandler.handleError(new Exception("race list missing!"));
        }
        return 0;
    }

    public String name;
    public String bio;
    private String imagePath;

    public Race(HashMap<String, Object> map, String key) {
        name = key;
        imagePath = "Data/gfx/races/" + HashMapExplorer.getString(map, key + ".imagePath");
        bio = HashMapExplorer.getString(map, key + ".bio");
    }

    //// debugging and temporary
    public Race(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public Image getImage(int sizeX, int sizeY) {
        String path = imagePath != null ? imagePath : "Data/gfx/" + folderName + "/" + name + ".png";
        File file = new File(path);

        //// if it does exist
        if (file.exists()) {
            return new Image(file.toURI().toString(),sizeX,sizeY,true,false);
        }
        //// if it doesn't exist
        return new Image("_NULL_.png",sizeX,sizeY,true,false);
    }

}
