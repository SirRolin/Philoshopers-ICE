package ICE.util;

import kotlin.Result;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class FileInterpreter {
    public static ArrayList<Object> parseFile(String path) {
        ArrayList<Object> output = new ArrayList<>();
        String fileText = null; //// TO DO load file.

        //// seperate the objects defined in the file
        ArrayList<String> objectsFound = FileInterpreter.findObjects(fileText, path, false);

        for (String s : objectsFound) { //// parse each object
            output.add(parse(s, path));
        }
        return output;
    }

    final static Pattern patternNumber = Pattern.compile("(?<whole>\\s*(?<key>\\w+)\\s*=\\s*(?<value>\\d+.?\\d*)\\s*)");
    final static Pattern patternString = Pattern.compile("(?<whole>\\s*(?<key>\\w+)\\s*=\\s*\"(?<value>[^\"]*)\")");
    final static Pattern patternObject = Pattern.compile("(?:\\s*\"([\\s\\w]+)\"|(\\d+))");

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
                return objectName;
            }
        }

        //// otherwise find all values in it
        int firstBracket = splitText[1].indexOf('{');
        int endBracket = splitText[1].lastIndexOf('}');
        if (firstBracket < endBracket) {
            String analyseText = splitText[1].substring(firstBracket + 1, endBracket);
            ArrayList<String> nest = findObjects(analyseText, path + "." + objectName, false);
            for (String nestedObject : nest) {
                String[] splitObject = nestedObject.split("=", 2);
                key = splitObject[0].trim();
                Object objects = parse(nestedObject, path + "." + key);

                Number keyWeight = 0;
                try { //// is it a weight_object? aka an item in a random_list
                    keyWeight = Float.parseFloat(key);
                    listsForOutput.add(new WeightedObject(keyWeight, objects));
                } catch (NumberFormatException ignored) {
                    listsForOutput.add(objects);
                }
                //analyseText = analyseText.replace(nestedObject, "");
            }
            if (listsForOutput.size() > 0) {
                mapsForOutput.put("list", listsForOutput);
            }
            return mapsForOutput;
        }
        if (isRandomList) {
            return new WeightedObject(weight, parse(splitText[1], path + "." + weight)); //// it's not actually the weight but the number value
        } else {
            return new HashMap<String, Object>().put(objectName, parse(splitText[1], path + "." + objectName));
        }
        /*
        Matcher numbers = patternNumber.matcher(text.trim());
        Matcher strings = patternString.matcher(text.trim());

        while (strings.find()) {
            String g1 = strings.group("key");
            String g2 = strings.group("value");
            return g2;
            //mapsForOutput.put("value", g2);
            //text = text.replace(strings.group("whole"), "");
        }
        while (numbers.find()) {
            String g1 = numbers.group("key");
            String g2 = numbers.group("value");
            return Float.parseFloat(g2);
            //mapsForOutput.put("value", Float.parseFloat(g2));
            //text = text.replace(numbers.group("whole"), "");
        }

        Matcher objects = patternObject.matcher(text.trim());

        //// any strings or numbers?
        if (objects.matches()) {
            objects.reset();
            for (MatchResult s : objects.results().toList()) {
                try { //// Find out if it's a number, if so add it as such
                    Number theNumber = Float.parseFloat(s.group(0));
                    listsForOutput.add(theNumber);
                } catch (NumberFormatException nfe) {
                    listsForOutput.add(s.group(0));
                }
            }
            if (isRandomList) {
                mapsForOutput.put("value", new WeightedObject(weight, listsForOutput));
                //mapsForOutput.put(objectName, new WeightedObject(weight, listsForOutput));
            } else {
                mapsForOutput.put("value", listsForOutput);
                //mapsForOutput.put(objectName, listsForOutput);
            }
        }
        if (listsForOutput.size() > 0) {
                mapsForOutput.put("list", listsForOutput);
            }
            if (randomListsForOutput.size() > 0) {
                mapsForOutput.put("random_list", randomListsForOutput);
            }
            return mapsForOutput;*/
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
                if (c == '\n') {
                    commented = false;
                    sb.append(c);
                    ++line;
                    if (brackets == 0) {
                        temp.setLength(0);
                    }
                }
            } else if (brackets == 0) { //// We haven't started an entry yet
                if (c == '\n') { //// keep track of line number and resetting
                    ++line;
                    if (temp.length() > 0) {
                        if (doThrowTokenError) {
                            temp.setLength(0);
                            ErrorHandler.handleError(new Exception("token error, missing { at line: " + line + " in file: " + path));
                        } else {
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
                }
                sb.append(c);
                if (brackets == 0) {
                    output.add(sb.toString());
                    sb.setLength(0); //// reset data
                }
            }
        }
        if (brackets > 0) {
            ErrorHandler.handleError(new Exception("error in " + path + " - missing " + brackets + " escape bracket(s) \"}\" on line: " + line));
            for (int i = 0; i < brackets; ++i) {
                sb.append('}');
            }
            output.add(sb.toString());
        }
        return output;
    }
}
