package philosophers_ice;

import java.io.Serializable;

public class GameState implements Serializable {

    public String name = "";
    int x = 0;
    int y = 0;
    public GameState(String name){
        this.name = name;
        StateSaver.saveGame(this);
    }
    GameState(){

    }

    public Player p1;
    public MapTile currentTile;
    public void visitMap(){
        currentTile = StateSaver.loadMap(this, x, y);
        currentTile.visit(p1);
    }

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
                int mapx = x + i - mid;
                int mapy = y - j + mid;
                inner[j] = StateSaver.loadMap(this, mapx, mapy);
            }
            output[i] = inner;
        }

        return output;
    }

}
