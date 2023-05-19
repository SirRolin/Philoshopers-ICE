package ICE.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class FileInterpreter {
    public static ArrayList<ArrayList<HashMap<String, Object>>> parseFolder(String path) {
        ArrayList<ArrayList<HashMap<String, Object>>> output = new ArrayList<>();
        File Folder = new File(path);

        if (Folder.isDirectory()) {
            File[] files = Folder.listFiles();
            for(File f: Folder.listFiles(File::isFile)) {
                output.add(parseFile(f.toPath().toString()));
            }
        }
        return output;
    }
    public static ArrayList<HashMap<String, Object>> parseFile(String path) {
        File fileOrFolder = new File(path);

        if (!fileOrFolder.isDirectory()) {
            try {
                //// get data from file
                File file = new File(path);
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                int bytesRead = fis.read(data);
                fis.close();
                String content = new String(data, 0, bytesRead);

                //// convert data to a hashmap
                ArrayList<HashMap<String, Object>> list = new ArrayList<>();
                for (String s: findObjects(content,path, false)) {
                    list.add(FileInterpreter.parse(s, path));
                }
                return list;
            } catch (IOException e) {
                ErrorHandler.handleError(e);
            }
        } else {
            ErrorHandler.handleError(new Exception("Can't load a folder as a file - use the parseFolder instead"));
        }
        return null;
    }


    public static ArrayList<HashMap<String, Object>> parseList(String text, String path) {
        return parseList(text, path, false);
    }
    public static ArrayList<HashMap<String, Object>> parseList(String text, String path, boolean careForFirstKey) {
        ArrayList<HashMap<String, Object>> output = new ArrayList<HashMap<String, Object>>();
        for (String s : findObjects(text, path, true)) {
            if(careForFirstKey){
                output.add(parse(s, path + " -> " + s));
            } else {
                int firstBracket = s.indexOf('{');
                int endBracket = s.lastIndexOf('}');
                if (firstBracket < endBracket) {
                    output.add(parse(s.substring(firstBracket + 1,  endBracket), path + " -> " + s));
                }
            }
        }

        return output;
    }
    /*
    public static Object parse(String text, String path) { //// usually returns HashMap
        HashMap<String, Object> mapsForOutput = new HashMap<String, Object>();
        ArrayList<Object> listsForOutput = new ArrayList<>();
        ArrayList<Object> randomListsForOutput = new ArrayList<>();
        String[] splitText = text.split("=", 2);
        String objectName = splitText[0].trim();
        String key;


        boolean isRandomList = false;
        Number weight = 0;
        try { //// is it a weight_object? aka an item in a random_list
            weight = Float.parseFloat(objectName);
            isRandomList = true;
        } catch (NumberFormatException ignored) {
            //// no it's not
        }

        //// if it's just a value
        if (splitText.length == 1) {
            if (isRandomList) {
                return weight; //// it's not actually the weight but the number value
            } else {
                return objectName.replace("\"", "");
            }
        }

        String analyseText;

        //// otherwise find all values in it
        int firstBracket = splitText[1].indexOf('{');
        int endBracket = splitText[1].lastIndexOf('}');
        if (firstBracket < endBracket) {
            analyseText = splitText[1].substring(firstBracket + 1, endBracket);
            ArrayList<String> nest = findObjects(analyseText, path + "." + objectName, false);
            for (String nestedObject : nest) {
                String[] splitObject = nestedObject.split("=", 2);
                key = splitObject[0].trim();
                Object objects = parse(nestedObject, path + "." + key);

                Number keyWeight = 0;
                try { //// is it a weight_object? aka an item in a random_list
                    keyWeight = Float.parseFloat(key);
                    //mapsForOutput.put(key, new WeightedObject(keyWeight, objects));
                    listsForOutput.add(objects);
                } catch (NumberFormatException ignored) {
                    mapsForOutput.put(key, objects);
                    //listsForOutput.add(objects);
                }
                analyseText = analyseText.replace(nestedObject, "");
            }
        } else {
            analyseText = splitText[1];
        }
        Matcher objects = patternObject.matcher(analyseText.trim());

        //// any strings or numbers?
        if (objects.matches()) {
            objects.reset();
            for (MatchResult s : objects.results().toList()) {
                try { //// Find out if it's a number, if so add it as such
                    return Float.parseFloat(s.group(0));
                } catch (NumberFormatException nfe) {
                    return s.group(0).replace("\"", "");
                }
            }
        }
        ////
        if (listsForOutput.size() > 0 || isRandomList) {
            if (isRandomList) {
                return new WeightedObject(weight, listsForOutput);
                //mapsForOutput.put("value", new WeightedObject(weight, listsForOutput));
            } else { //// if it has a list of things
                mapsForOutput.put("value", listsForOutput);
            }
        }
        return mapsForOutput;
//        if (isRandomList) {
//            return new WeightedObject(weight, parse(splitText[1], path + "." + weight)); //// it's not actually the weight but the number value
//        } else {
//        mapsForOutput.put(objectName, parse(splitText[1].trim(), path + "." + objectName));
//        return parse(splitText[1], path + "." + objectName);
//        }
//
//        Matcher numbers = patternNumber.matcher(text.trim());
//        Matcher strings = patternString.matcher(text.trim());
//
//        while (strings.find()) {
//            String g1 = strings.group("key");
//            String g2 = strings.group("value");
//            return g2;
//            //mapsForOutput.put("value", g2);
//            //text = text.replace(strings.group("whole"), "");
//        }
//        while (numbers.find()) {
//            String g1 = numbers.group("key");
//            String g2 = numbers.group("value");
//            return Float.parseFloat(g2);
//            //mapsForOutput.put("value", Float.parseFloat(g2));
//            //text = text.replace(numbers.group("whole"), "");
//        }
//
//        if (listsForOutput.size() > 0) {
//            mapsForOutput.put("list", listsForOutput);
//        }
//        if (randomListsForOutput.size() > 0) {
//            mapsForOutput.put("random_list", randomListsForOutput);
//        }
//        return mapsForOutput;
    }*/

    private static HashMap<String, Object> parse(String text, String path) { //// usually returns HashMap
        HashMap<String, Object> mapsForOutput = new HashMap<String, Object>();
        ArrayList<Object> listsForOutput = new ArrayList<>();


        //// Extract all the something = { something }
        text = ExtractElements(text, mapsForOutput, listsForOutput, path);

        //// Extract all the word = something
        text = ExtractProperty(text, mapsForOutput);

        //// extracts all the number = something
        text = ExtractRandomNumbers(text, listsForOutput);

        //// extracts all the number = something
        text = ExtractBoolean(text, mapsForOutput);

        //// extracts all the once standing alone
        text = ExtractList(text, listsForOutput);
        if (!listsForOutput.isEmpty()) {
            mapsForOutput.put("list", listsForOutput);
        }
        return mapsForOutput;
    }


    private static String ExtractList(String text, ArrayList<Object> listsForOutput) {
        for(String s: text.split("[ \r\n]+",-1)){
            if (!s.isBlank()) {
                listsForOutput.add(tryParseFloat(s, false));
                text = text.replace(s, "");
            }
        }
        return text;
    }
    final static Pattern patternBoolean = Pattern.compile("(?<whole>\\s*(?<key>\\w+)\\s*=\\s*(?<bool>yes|no))");
    private static String ExtractBoolean(String text, HashMap<String, Object> mapsForOutput) {
        Matcher booleans = patternBoolean.matcher(text);
        while (booleans.find()) {
            String g1 = booleans.group("key");
            if(booleans.group("bool").equals("yes")){
                mapsForOutput.put(g1, true);
            } else {
                mapsForOutput.put(g1, false);
            }
            text = text.replace(booleans.group("whole"), "");
        }
        return text;
    }

    final static Pattern patternMap = Pattern.compile("(?<whole>(?<firstReplace>\\s*(?<key>\\w+)\\s*=\\s*\\{)(?s)(?<value>.*))(?-s)");
    private static String ExtractElements(String text, HashMap<String, Object> map, ArrayList<Object> list, String path) {
        Matcher elements = patternMap.matcher(text);
        while (elements.find()) {
            String g1 = elements.group("key");
            String g2 = elements.group("value");
            int charToClosingBracket = charsToPairedClosingBracket(g2);
            g2 = g2.substring(0, charToClosingBracket - 1);
            if (isNumeric(g1)) {
                list.add(new WeightedObject((Number) tryParseFloat(g1,true),parse(g2, path + " -> " + g1 + "(" + g2 + ")")));
            } else {
                map.put(g1, parse(g2, path + " -> " + g1));
            }
            String replace = elements.group("firstReplace") + g2 + "}";
            text = text.replace(replace, "");
            elements = patternMap.matcher(text);
        }
        return text;
    }

    final static Pattern patternProperty = Pattern.compile("(?<whole>\\s*(?<key>\\w+)\\s*=\\s*(?<value>\"[^\"]*\"|\\d+.?\\d*))");
    private static String ExtractProperty(String text, HashMap<String, Object> mapsForOutput) {
        Matcher strings = patternProperty.matcher(text.trim());
        while (strings.find()) {
            String g1 = strings.group("key");
            String g2 = strings.group("value");
            mapsForOutput.put(g1, tryParseFloat(g2, false));
            text = text.replace(strings.group("whole"), "");
        }
        return text;
    }
    final static Pattern patternNumber = Pattern.compile("(?<whole>\\s*(?<key>\\d+.?\\d*)\\s*=\\s*(?<value>\\d+.?\\d*)\\s*)");
    private static String ExtractRandomNumbers(String text, ArrayList<Object> listsForOutput) {
        Matcher numbers = patternNumber.matcher(text.trim());
        while (numbers.find()) {
            String g1 = numbers.group("key");
            String g2 = numbers.group("value");
            listsForOutput.add(
                    new WeightedObject(
                            (Number) tryParseFloat(g1, true),
                            tryParseFloat(g2, false)));

            text = text.replace(numbers.group("whole"), "");
        }
        return text;
    }

    private static Object tryParseFloat(String text, boolean defaultIsZero){
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException ignored) {
            if(defaultIsZero){
                return 0;
            } else {
                return text.replace("\"","");
            }
        }
    }

    private static boolean isNumeric(String text){
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException ignored){
            return false;
        }
    }

    private static int charsToPairedClosingBracket(String text) {
        int brackets = 1;
        boolean quoted = false;
        int output = 0;
        for (char c : text.toCharArray()) {
            output++;
            if (c == '"') { //// enter and leave quote
                quoted = !quoted;
            } else if (quoted) { //// while quoted just continue
            } else if (c == '{') { //// "recursive" increase bracket tracking
                ++brackets;
            } else if (c == '}') { //// "recursive" decrease bracket tracking
                --brackets;
            }
            if (brackets == 0) {
                return output;
            }
        }
        return -1; ////bracket was not found
    }


    public static ArrayList<String> findObjects(String text, String path){
        ArrayList<String> output = new ArrayList<String>();
        Matcher elements = patternMap.matcher(text);
        while (elements.find()) {
            //// can also use firstReplace
            String g1 = elements.group("key");
            String g2 = elements.group("value");
            int charToClosingBracket = charsToPairedClosingBracket(g2);
            g2 = g2.substring(0, charToClosingBracket);
            output.add(g1 + " = {" + g2);
        }
        return output;
    }

    //// lines is the lines of text, path is just for error handling.
    public static ArrayList<String> findObjects(String text, String path, boolean doThrowTokenError) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        ArrayList<String> output = new ArrayList<String>();
        int line = 0;
        int brackets = 0;
        boolean quoted = false;
        boolean commented = false;
        for (char c : text.toCharArray()) {

            if (!quoted && c == '#') { //// Start a comment - only while not quoted though
                commented = true;
            } else if (commented) { //// while commenting don't activate any other logic until we are not commenting anymore
                if (c == '\n' || c == '\r') {
                    commented = false;
                    ++line;
                    if (brackets == 0) {
                        temp.setLength(0);
                    } else {
                        sb.append(c);
                    }
                }
            } else if (brackets == 0) { //// We haven't started an entry yet
                if (c == '\n' || c == '\r') { //// keep track of line number and resetting
                    ++line;
                    if (!temp.isEmpty()) {
                        if (doThrowTokenError) {
                            temp.setLength(0);
                            ErrorHandler.handleError(new Exception("token error, missing { at line: " + line + " in file: " + path));
                        } else {
                            if (temp.indexOf("=") > 1)
                                output.add(temp.toString());
                            temp.setLength(0); //// reset data
                        }
                    }
                } else if (c == '{') { //// starting a bracket
                    sb.append(temp).append(c);
                    temp.setLength(0);
                    ++brackets;
                } else if (c == '}') { //// error
                    if (doThrowTokenError)
                        ErrorHandler.handleError(new Exception("token error, missing { before } at line: " + line + " in file: " + path));
                } else if (temp.length() > 0 || (c != ' ' && c != '\t')) { //// otherwise keep track of the line
                    temp.append(c);
                }
            } else {
                if (c == '"') { //// enter and leave quote
                    quoted = !quoted;
                } else if (quoted) { //// while quoted just continue
                } else if (c == '{') { //// "recursive" increase bracket tracking
                    ++brackets;
                } else if (c == '}') { //// "recursive" decrease bracket tracking
                    --brackets;
                } else if (c == '\n' || c == '\r'){
                    ++line;
                }
                sb.append(c);
                if (brackets == 0) {
                    output.add(sb.toString());
                    sb.setLength(0); //// reset data
                    temp.setLength(0);
                }
            }
        }
        if (brackets > 0) {
            ErrorHandler.handleError(new Exception("error in " + path + " - missing " + brackets + " escape bracket(s) \"}\" on line: " + line));
            for (int i = 0; i < brackets; ++i) {
                sb.append('}');
            }
            output.add(sb.toString());
            sb.setLength(0); //// reset data
            temp.setLength(0);
        }
        return output;
    }
}
