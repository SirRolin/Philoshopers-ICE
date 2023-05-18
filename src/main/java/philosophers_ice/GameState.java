package philosophers_ice;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    public ArrayList<String> commandsList;
    public String name = "";
    transient MapTile[][] maptile = getMinimap(9);
    int x = 0;
    int y = 0;
    public GameState(String name){
        this.name = name;

        StateSaver.saveGame(this);
        this.commandsList = new ArrayList<>();
        commandsList.add("go to");
        commandsList.add("x");
        commandsList.add("inspect");
        commandsList.add("inventory");
        commandsList.add("i");

        //StateSaver.saveGame(this);

    }
    GameState(){

    }

    //HashMap<philosophers_ice.GUI> guis;

    GUI activeGUI;

    public Player p1;

    MapTile[][] getMinimap(int size){
        MapTile output[][] = new MapTile[size*2-1][size*2-1];
        for(int i = 0; i < size*2-1; ++i){
            for(int j = 0; j < size*2-1; ++j){
                output[i][j] = StateSaver.loadMap(this, i + x - size*2, j + y - size*2);
            }
        }

        return output;
    }

}
