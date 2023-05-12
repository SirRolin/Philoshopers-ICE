import ICE.util.ErrorHandler;

import java.io.Serializable;

public class Philosophers_ICE {
    public static void main(String[] args){
        for(String s: args){
            String[] split = s.split("=");
            switch(split[0]){
                case "errorLog" -> {
                    ErrorHandler.errorLogPath = split[1];
                }
            }
        }

    }

}
