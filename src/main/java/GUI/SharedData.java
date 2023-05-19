package GUI;

import ICE.util.FileInterpreter;
import ICE.util.HashMapExplorer;
import philosophers_ice.GameState;
import philosophers_ice.Race;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class SharedData {
    public static GameState gs;

    public static ArrayList<HashMap<String, Object>> defines = new ArrayList<>();

    public static String getDefineString(String key){
        for(HashMap<String, Object> def: defines){
            String temp = (String) HashMapExplorer.getString(def,key);
            if(temp.equals(key)){
                return temp;
            }
        }
        return null;
    }
    public static void load(){
        if(defines.isEmpty()) {
            defines.addAll(FileInterpreter.parseFolder("Data/common/defines/"));
        }
    }
    public static void reload(){
        defines.clear();
        load();
    }

}
