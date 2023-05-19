package ICE.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class HashMapExplorer {
    /**
     * Deprecated (lasted 20 minutes), but might be usefui if we need it instead of ListMapToforEach
     * @param list
     *  list with a HashMap\<String,Object>
     * @param key
     *  list to look for in the HashMap
     * @return
     *  every HashMap that has the key
     */
    public static ArrayList<HashMap<String, Object>> getMapFromList(ArrayList<HashMap<String, Object>> list, String key) {
        ArrayList<HashMap<String, Object>> output = new ArrayList<HashMap<String, Object>>();
        for (HashMap<String, Object> map : list) {
            Object Object = getToObject(map, key);
            if (Object instanceof HashMap<?, ?> map1) {
                output.add((HashMap<String, Object>) map1);
            }
        }
        return output;
    }

    /**
     * Used on a listMap where the key is important
     * @param inputList
     * ArrayList<HashMap<String, Object>>
     * @param key
     * looks for this in each map
     * @param biConsumer
     * BiConsumer which is called whenever a key is found in the listmap
     */
    public static void ListMapToforEach(ArrayList<HashMap<String, Object>> inputList, String key, BiConsumer<String, HashMap<String, Object>> biConsumer) {
        for (HashMap<String, Object> map : inputList) {
            Set<String> keys = map.keySet();
            for (String s : keys) {
                HashMap<String, Object> innerMap = HashMapExplorer.getMap(map, s);
                if (innerMap != null) {
                    biConsumer.accept(s, innerMap);
                }
            }
        }
    }

    /**
     * Used on a listMap where the key is NOT important
     * @param inputList
     * ArrayList<HashMap<String, Object>>
     * @param key
     * looks for this in each map
     * @param consumer
     * Consumer which is called whenever a key is found in the listmap
     */
    public static <T> void ListMapToforEach(ArrayList<HashMap<String, Object>> inputList, String key, Consumer<HashMap<String, Object>> consumer) {
        for (HashMap<String, Object> map : inputList) {
            Set<String> keys = map.keySet();
            for (String s : keys) {
                HashMap<String, Object> innerMap = HashMapExplorer.getMap(map, s);
                if (innerMap != null) {
                    consumer.accept(innerMap);
                }
            }
        }
    }

    private static HashMap<String, Object> getMap(Object obj, String key) {
        if (obj instanceof HashMap<?, ?> map) {
            Object Object = getToObject(map, key);
            if (Object instanceof HashMap<?, ?> map1) {
                return (HashMap<String, Object>) map1;
            } else {
                return null;
            }
        }
        return null;
    }

    public static String getImage(Object obj, String key) {
        if (obj instanceof HashMap<?, ?> map) {
            Object Object = getToObject(map, key);
            if (Object instanceof String theString) {
                return theString;
            } else {
                return "_NULL_.png";
            }
        }
        return "_NULL_.png";
    }

    public static String getString(Object obj, String key) {
        if (obj instanceof HashMap<?, ?> map) {
            Object Object = getToObject(map, key);
            if (Object instanceof String theString) {
                return theString;
            } else {
                return key + "_needs_a_string";
            }
        }
        return key + "_missing";
    }

    public static boolean getBoolean(Object obj, String key, boolean defaultValue) {
        if (obj instanceof HashMap<?, ?> map) {
            Object Object = getToObject(map, key);
            if (Object instanceof Boolean bool) {
                return (boolean) bool;
            } else {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static Number getNumber(Object obj, String key) {
        if (obj instanceof HashMap<?, ?> map) {
            Object Object = getToObject(map, key);
            if (Object instanceof Number theNumber) {
                return theNumber;
            } else {
                return 0;
            }
        }
        return 0;
    }

    public static ArrayList<Object> getList(Object obj, String key) {
        if (obj instanceof HashMap<?, ?> map) {
            Object Object = getToObject(map, key);
            if (Object instanceof ArrayList<? extends Object> lst) {
                return (ArrayList<Object>) lst;
            } else {
                //// in case the list doesn't exist the for each will not iterate
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

    private static Object getToObject(HashMap<?, ?> map, String key) {
        String[] keys = key.trim().split("\\."); //// Split at dots
        HashMap<?, ?> nestedMap = map;
        for (int i = 0; i < keys.length - 1; ++i) { //// every dot except the last needs to nest the map
            if (nestedMap.containsKey(keys[i])) {
                if (nestedMap.get(keys[i]) instanceof HashMap<?, ?> tempMap) {
                    nestedMap = tempMap;
                }
            }
        }
        //// we are at the end of nesting and just need the String/Number
        return nestedMap.get(keys[keys.length - 1]);
    }
}
