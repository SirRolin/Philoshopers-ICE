package ICE.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class FileInterpreter {
    public static ArrayList<HashMap<String, Object>> getHashMapFromFile(String path){
        ArrayList<HashMap<String, Object>> output = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();


        return output;
    }

    //// lines is the lines of text, path is just for error handling.
    public static ArrayList<String> splitObjects(ArrayList<String> lines, String path){
        StringBuilder sb = new StringBuilder();
        StringBuilder thisLineSB = new StringBuilder();
        ArrayList<String> output = new ArrayList<String>();
        int line = 0;
        int brackets = 0;
        boolean quoted = false;
        boolean commented = false;
        for(String text: lines) {
            ++line;
            for (char c : text.toCharArray()) {
                if (commented && c == '\n'){
                    commented = false;
                } else if (!quoted && c == '#') { // Commment
                    commented = true;
                } else if (!quoted && c == '{') {
                    brackets++;
                } else if (!quoted && c == '}' && brackets > 0) {
                    brackets--;
                    if(brackets == 0){
                        output.add(sb.toString());
                    }
                } else if (!quoted && c == '}' & brackets == 0){
                    ErrorHandler.handleError(new Exception("error in " + path + " - missing bracket \"{\", escape bracket \"}\" missing bracket \"{\" on line: " + line));
                } else if (c == '"'){
                    quoted = !quoted;
                }
                if (!commented) {
                    sb.append(c);
                }
            }
        }
        if (brackets > 0){
            ErrorHandler.handleError(new Exception("error in " + path + " - missing escape bracket \"}\" on line: " + line));
            for(int i = 0; i < brackets; ++i){
                sb.append('}');
            }
            output.add(sb.toString());
        }
        return output;
    }
}
