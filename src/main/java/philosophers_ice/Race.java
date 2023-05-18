package philosophers_ice;

import ICE.util.FileInterpreter;
import ICE.util.HashMapExplorer;


import java.util.HashMap;

public class Race {

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Race implements Serializable {

    private static final ArrayList<Race> listOfUs = new ArrayList<>();
    public static Race getRace(String nameOfRace){
        for(Race race: listOfUs){
            if(race.name.equals(nameOfRace)){
                return race;
            }
        }
        return null;
    }
    //// remember NOT to override any elements
    public static ArrayList<Race> getRaces(){
        return listOfUs;
    }
    public static void load(){
        if(listOfUs.isEmpty()) {
            for (HashMap<String, Object> s : FileInterpreter.parseFolder("Data/common/races/", true)) {
                String key = (String) s.keySet().toArray()[0];
                listOfUs.add(new Race(HashMapExplorer.getMap(s,key), key));
            }
        }
    }
    public static void reload(){
        listOfUs.clear();
        load();
    }


    public String name;
    private String imagePath;

    public Race(HashMap<String, Object> map, String key){
        name = key;
        imagePath = HashMapExplorer.getString(map, key + ".imagePath");
    }

    //// debugging and temporary
    public Race(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
    }

}
