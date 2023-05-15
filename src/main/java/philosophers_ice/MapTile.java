package philosophers_ice;

import java.io.Serializable;
import java.util.ArrayList;

public class MapTile implements Serializable {

    String imagePath = "";

    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<Boon> boons = new ArrayList<Boon>();

}
