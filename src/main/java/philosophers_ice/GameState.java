package philosophers_ice;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {

    public String name = "";
    transient MapTile[][] maptile;
    int x = 0;
    int y = 0;
    public GameState(String name){
        this.name = name;
         maptile = getMinimap(9, 5);
        //StateSaver.saveGame(this);
    }
    GameState(){

    }

    //HashMap<philosophers_ice.GUI> guis;

    GUI activeGUI;

    public Player p1;

<<<<<<< Updated upstream
    public MapTile[][] getMinimap(int size){
        MapTile[][] output = new MapTile[size*2-1][size*2-1];
        for(int i = 0; i < size*2-1; ++i){
            for(int j = 0; j < size*2-1; ++j){
                output[i][j] = StateSaver.loadMap(this, i + x - size*2, j + y - size*2);
=======
    public MapTile[][] getMinimap(int size, int mid){
        if(mid <= 0){
            mid = 0;
        } else if(mid > size){
            mid = size-1;
        }
        MapTile[][] output = new MapTile[size][size];
        for(int i = 0; i < size; ++i){
            MapTile[] inner = new MapTile[size];
            for(int j = 0; j < size; ++j){
                inner[j] = StateSaver.loadMap(this, i + x - (mid - 1), j + y - (mid - 1));
>>>>>>> Stashed changes
            }
            output[i] = inner;
        }

        return output;
    }

}
