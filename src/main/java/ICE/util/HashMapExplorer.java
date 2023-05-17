package ICE.util;

import java.util.HashMap;

public abstract class HashMapExplorer {
    public static String getString(Object obj, String key){
        if(obj instanceof HashMap<?, ?> map){
            Object Object = getToObject(map, key);
            if (Object instanceof String theString) {
                return theString;
            } else {
                return key + "_needs_a_string";
            }
        }
        return key + "_missing";
    }

    public static Number getNumber(Object obj, String key){
        if(obj instanceof HashMap<?, ?> map){
            Object Object = getToObject(map, key);
            if (Object instanceof Number theNumber) {
                return theNumber;
            } else {
                return 0;
            }
        }
        return 0;
    }

    private static Object getToObject(HashMap<?,?> map, String key){
        String[] keys = key.trim().split("\\."); //// Split at dots
        HashMap<?,?> nestedMap = map;
        for(int i = 0; i < keys.length - 1;++i){ //// every dot except the last needs to nest the map
            if (nestedMap.containsKey(keys[i])) {
                if(nestedMap.get(keys[i]) instanceof HashMap<?,?> tempMap) {
                    nestedMap = tempMap;
                }
            }
        }
        //// we are at the end of nesting and just need the String/Number
        return nestedMap.get(keys[keys.length - 1]);
    }
}
