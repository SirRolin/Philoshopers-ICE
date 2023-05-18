package philosophers_ice;

import ICE.util.ErrorHandler;
import ICE.util.FileIO;
import GUI.Main;

import java.util.ArrayList;

public class Launcher {
    public static void main(String[] args){
        for(String s: args){
            String[] split = s.split("=");
            switch(split[0]){
                case "errorLog" -> {
                    ErrorHandler.errorLogPath = split[1];
                }


                case "debugLog" ->{
                    ErrorHandler.debugLogPath = split[1];
                }

                case "saveLoadFolder" ->{
                    FileIO.defaultPath = split[1];
                }
            }
        }
        Main.main(args);
    }
}
