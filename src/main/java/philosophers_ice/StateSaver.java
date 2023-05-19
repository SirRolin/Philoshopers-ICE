package philosophers_ice;

import ICE.util.FileIO;
import philosophers_ice.GameState;
import philosophers_ice.MapTile;

public abstract class StateSaver extends FileIO {
    public static Boolean saveGame(GameState gs) {
        return writeSerialised(gs, "saves/" + gs.name + "/save.save");
    }

    public static GameState loadGame(String name) {
        return readSerialised("saves/" + name + "/save.save");
    }

    public static GameState newGame(String name) {
        GameState gs = new GameState(name);
        MapTile mt = new MapTile("grass");
        saveMap(gs, mt,0,0);
        saveMap(gs, mt,0,1);
        saveMap(gs, mt,0,2);
        saveMap(gs, mt,0,3);
        mt.structure = "Alter";
        saveMap(gs, mt,1,3);
        return gs;
    }

    public static Boolean saveMap(GameState gs, MapTile map, int x, int y) {
        return writeSerialised(map, "saves/" + gs.name + "/mapTiles/" + x + "_" + y + ".map");
    }

    public static MapTile loadMap(GameState gs, int x, int y) {
        return readSerialised("saves/" + gs.name + "/mapTiles/" + x + "_" + y + ".map");
    }
}
