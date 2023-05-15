package ICE.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class FileInterpreter {
    public static ArrayList<HashMap<String, Object>> getHashMapFromFile(String path){
        ArrayList<HashMap<String, Object>> output = new ArrayList<HashMap<String, Object>>();


        return output;
    }

    //// lines is the lines of text, path is just for error handling.
    public static ArrayList<String> splitObjects(ArrayList<String> lines, String path){
        StringBuilder sb = new StringBuilder();
        ArrayList<String> output = new ArrayList<String>();
        int line = 0;
        int brackets = 0;
        boolean quoted = false;
        boolean commented = false;
        boolean removedContent = false;
        for(String text: lines) {
            ++line;
            for (char c : text.toCharArray()) {
                removedContent = false;
                if (commented && c == '\n'){
                    commented = false;
                } else if (!quoted && c == '#') { // Commment
                    commented = true;
                } else if (!quoted && c == '{') {
                    brackets++;
                } else if (!quoted && c == '}' && brackets > 0) {
                    brackets--;
                    if(brackets == 0){
                        output.add(sb.append(c).toString());
                        sb.setLength(0);
                    }
                } else if (!quoted && c == '}' && brackets == 0){
                    ErrorHandler.handleError(new Exception("error in " + path + " - missing bracket \"{\", escape bracket \"}\" missing bracket \"{\" on line: " + line));
                    removedContent = true;
                } else if (c == '"'){
                    quoted = !quoted;
                }
                if (!commented && !removedContent) {
                    sb.append(c);
                }
            }
            sb.append("\n");
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

    public static HashMap<String, Object> HashMap(ArrayList<String> lines, String path, int firstLine){
        return HashMap(lines, path, firstLine, 0);
    }
    public static HashMap<String, Object> HashMap(ArrayList<String> lines, String path, int firstLine, int bracket){
        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> innerMap = new HashMap<String, Object>();
        StringBuilder currentWord = new StringBuilder();
        String key = "";
        int line = firstLine;
        boolean quoted = false;
        boolean spaceDetected = false;
        for(String s: lines){
            ++line;
            for(char c: s.toCharArray()){
                if(c=='"'){
                    quoted = !quoted;

                } else if(!quoted) {
                    if (c == ' ' || c == '\n') {
                        if (!key.equals("")) {
                            innerMap.put(key, currentWord.toString());
                        }
                        spaceDetected = true;
                    } else if (c == '=') {
                        if (!currentWord.isEmpty()) {
                            ErrorHandler.handleError(new Exception("no key found on line: " + line + " - of: " + path));
                        } else {
                            key = currentWord.toString();
                            currentWord.setLength(0);
                        }
                    } else if (c == '{') {
                        /// to do
                    } else {
                        if (spaceDetected && !currentWord.isEmpty()) {
                            currentWord.setLength(0); //// clears the StringBuilder
                        }
                        currentWord.append(c);
                    }
                } else {
                    currentWord.append(c);
                }
            }
        }
        return map;
    }
}
