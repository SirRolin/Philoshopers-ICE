package philosophers_ice;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    public ArrayList<String> commandsList;
    public String name = "";
    MapTile[][] maptile = getMinimap();
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
    }
    GameState(){

    }

    //HashMap<philosophers_ice.GUI> guis;

    GUI activeGUI;

    public Player p1;



    MapTile[][] getMinimap(){
        MapTile output[][] = new MapTile[5][5];
        for(int i = 0; i < 5; ++i){
            for(int j = 0; j < 5; ++j){
                output[i][j] = new MapTile();
            }
        }

        return output;
    }

}
