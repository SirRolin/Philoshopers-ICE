import ICE.util.ErrorHandler;
import ICE.util.FileIO;

public class Launcher {
    public static void main(String[] args){
        for(String s: args){
            String[] split = s.split("=");
            switch(split[0]){
                case "errorLog" -> {
                    ErrorHandler.errorLogPath = split[1];
                }
            }
        }
        GameState newGS = new GameState();
        newGS.p1 = new Player("test");
        FileIO.writeSerialised(newGS, "Data/Test.sav");

        GameState loadedGS = FileIO.readSerialised("Data/Test.sav");
        System.out.println(loadedGS.p1.name);
    }
}
