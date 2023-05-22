package hej;

import ICE.util.FileInterpreter;
import ICE.util.HashMapExplorer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFileInterpreter {
    @Test
    void testFindObjects(){
        String first = """
            enemy = {
                name = "small rat"
                imagePath = "Small Rat.png"
                description = "a small rat"
                defence = 2
                damage = {
                   min = 2
                   max = 3
                }
            }""";
        String second = """
            enemy = {
                name = "Chunkey rat"
                imagePath = "Chunkey Rat.png"
                description = "a chunkey rat"
                defence = 11
                damage = {
                   min = 5
                   max = 9
                }
            }""";
        ArrayList<String> objectsFound = FileInterpreter.findObjects(first + "\n\n " + second, "UnitTest testFindObjects of FileInterpreter", false);
        Assertions.assertEquals(first, objectsFound.get(0));
        Assertions.assertEquals(second, objectsFound.get(1));
    }
    @Test
    void testParse(){
        String first = """
            enemy = {
                name = "small rat"
                droplist = {
                    "Knife"
                    2 = {"yes?"}
                    0 = {}
                }
                defence = 2
                damage = {
                   min = 2
                   max = 3
                }
            }""";
        ArrayList<String> objectsFound = FileInterpreter.findObjects(first, "UnitTest testParse of FileInterpreter", false);
        ArrayList<HashMap<String, Object>> hashMap = FileInterpreter.parseList(objectsFound.get(0), "UnitTest testParse of FileInterpreter");

        Assertions.assertEquals("small rat", HashMapExplorer.getString(hashMap.get(0), "name"));
        Assertions.assertEquals(2, HashMapExplorer.getNumber(hashMap.get(0), "defence").intValue());
        Assertions.assertEquals(2.0, HashMapExplorer.getNumber(hashMap.get(0),"damage.min").floatValue());
    }

    @Test
    void testRegex(){
        final Pattern patternString = Pattern.compile("(?<whole>\\s*(?<key>\\w+)\\s*=\\s*\"(?<value>[^\"]*)\")");
        String thingToTest = "name = \"test rat\"";
        System.out.println(patternString.matcher(thingToTest).matches());
    }

    @Test
    void testsubstring(){
        String first = """
            items = {
                "small rat"
                "Great Sword"
            }""";
        ArrayList<HashMap<String, Object>> hashMap = FileInterpreter.parseList(first, "UnitTest testParse of FileInterpreter");
        Assertions.assertEquals("small rat", HashMapExplorer.getList(hashMap.get(0), "list").get(0));
    }
}
