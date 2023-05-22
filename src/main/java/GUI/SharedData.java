package GUI;

import ICE.util.FileInterpreter;
import ICE.util.HashMapExplorer;
import philosophers_ice.GameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public abstract class SharedData {
    public static GameState gs;
    public static GameSceneController gsc;

    private static HashMap<String, Object> defines = new HashMap<String, Object>();

    public static String getDefineString(String key){
        if(defines.isEmpty()) {
            load();
        }
        return HashMapExplorer.getString(defines,key);
    }
    public static void load(){
        for (ArrayList<HashMap<String,Object>> lst: FileInterpreter.parseFolder("Data/common/defines/")){
            HashMapExplorer.ListMapToForEach(lst, (s, map) -> {
                for (Object inner: ((HashMap<String, Object>)map).keySet()) {
                    defines.put((String) inner, ((HashMap<String, Object>) map).get(inner));
                }
            });
        }
    }
    public static void reload(){
        defines.clear();
        load();
    }

}
