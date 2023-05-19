package ICE.util;

import philosophers_ice.Item;

import java.util.ArrayList;

public class RngHandler {

    public static <T> void WeightedObjectsToList(ArrayList<WeightedObject> chanceList, ArrayList<T> loot) {
        float totalWeight = 0;
        for (WeightedObject wo : chanceList) {
            if (wo.weight.floatValue() > 0f) {
                totalWeight += (float) wo.weight;
            }
        }
        if (totalWeight > 0) {
            java.util.Random rng = new java.util.Random();
            Float chance = rng.nextFloat(0, totalWeight);
            float weightSoFar = 0;
            for(int ite = 0; ite < chanceList.size(); ++ite) {
                if (chanceList.get(ite).weight.floatValue() + weightSoFar <= chance) {
                    Object tempObj = chanceList.get(ite).obj;
                    while (tempObj instanceof ArrayList<?> lst2) {
                        for (Object obj2 : lst2) {
                            if (chanceList.get(ite).obj instanceof ArrayList<?>) {

                            }
                            if (tempObj != null) {
                                loot.add((T) tempObj);
                            }
                        }
                    }
                    loot.add((T) chanceList.get(ite).obj);
                    break;
                }
                weightSoFar += chanceList.get(ite).weight.floatValue();

            }
        }
    }
/*
    public static <T> void WeightedObjectsToList(float totalWeight, ArrayList<Object> chanceList, ArrayList<T> loot) {
        if (totalWeight > 0) {
            java.util.Random rng = new java.util.Random();
            Float chance = rng.nextFloat(0, totalWeight);
            for(int ite = 0; ite < chanceList.size(); ++ite) {
                if(chanceList.get(ite) instanceof WeightedObject wo){
                    if (wo.weight.floatValue() <= chance) {
                        Object tempObj = wo.obj;
                        while (tempObj instanceof ArrayList<?> lst2) {
                            for (Object obj2 : lst2) {
                                if (wo.obj instanceof ArrayList<?>) {

                                }
                                if (tempObj != null) {
                                    loot.add((T) tempObj);
                                }
                            }
                        }
                        loot.add((T) wo.obj);
                        break;
                    }
                } else {
                    loot.add((T) chanceList.get(ite));
                }

            }
        }
    }*/
}
