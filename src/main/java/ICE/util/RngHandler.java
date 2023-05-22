package ICE.util;

import philosophers_ice.Item;

import java.util.ArrayList;

public class RngHandler {

    public static <T> void WeightedObjectsToList(ArrayList<WeightedObject> chanceList, ArrayList<T> loot, float dropChances) {
        java.util.Random rng = new java.util.Random();
        float drops = rng.nextFloat(0, 1);
        while (dropChances > drops) {
            float totalWeight = 0;
            for (WeightedObject wo : chanceList) {
                if (wo.weight.floatValue() > 0f) {
                    totalWeight += wo.weight.floatValue();
                }
            }
            if (totalWeight > 0) {
                float chance = rng.nextFloat(0, totalWeight);
                float weightSoFar = 0;
                for (int ite = 0; ite < chanceList.size(); ++ite) {
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
            ++drops;
        }
    }
}
