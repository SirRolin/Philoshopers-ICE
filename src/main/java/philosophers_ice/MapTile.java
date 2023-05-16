package philosophers_ice;

import java.io.Serializable;
import java.util.ArrayList;

public class MapTile implements Serializable {

    String imagePath = "";

    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<EffectCard> effectCards = new ArrayList<EffectCard>();

}
