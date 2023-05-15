package philosophers_ice;

import ICE.util.FileIO;
import philosophers_ice.GameState;
import philosophers_ice.MapTile;

public class StateSaver extends FileIO {
    Boolean savePlace(MapTile map, int x, int y){
        return writeSerialised(map, "saves/mapTiles/" + x + "_" + y + ".map");
    }
    Boolean saveGame(GameState gs){
        return writeSerialised(gs, "saves/" + gs.name + ".save");
    }
    GameState loadGame(String name){
        return readSerialised("saves/" + name + ".save");
    }
    GameState newGame(){
        return new GameState();
    }
    MapTile loadMap(int x, int y){
        return readSerialised("saves/mapTiles/" + x + "_" + y + ".map");
    }
}
