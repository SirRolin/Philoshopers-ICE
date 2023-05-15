package philosophers_ice;

import ICE.util.FileIO;
import philosophers_ice.GameState;
import philosophers_ice.MapTile;

public abstract class StateSaver extends FileIO {
    static Boolean saveGame(GameState gs){
        return writeSerialised(gs, "saves/" + gs.name + "/save.save");
    }
    static GameState loadGame(String name){
        return readSerialised("saves/" + name + "/save.save");
    }
    static GameState newGame(String name){
        return new GameState(name);
    }
    static Boolean saveMap(GameState gs, MapTile map){
        return writeSerialised(map, "saves/" + gs.name + "/mapTiles/" + x + "_" + y + ".map");
    }
    static MapTile loadMap(GameState gs){
        return readSerialised("saves/" + gs.name + "/mapTiles/" + gs.x + "_" + gs.y + ".map");
    }
}
