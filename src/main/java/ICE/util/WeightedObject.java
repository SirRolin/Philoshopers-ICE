package ICE.util;

import java.io.Serializable;

public class WeightedObject implements Serializable {
    public Number weight;
    public Object obj;
    WeightedObject(Number weight, Object obj){
        this.weight = weight;
        this.obj = obj;
    }
}
