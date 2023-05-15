import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GameState implements Serializable {

    public String name = "Tess";
    MapTile[][] maptile = getMinimap();

    ArrayList<MapTile> mapTilePool = new ArrayList<>();

    HashMap<GUI> guis;

    GUI activeGUI;

    public Player p1;

    MapTile[][] getMinimap(){
        MapTile output[][] = new MapTile[5][5];
        for(int i = 0; i < 5; ++i){

            for(int j = 0; j < 5; ++j){
                output[i-2][-2] = new MapTile();
            }
        }

        return output;
    }

}
