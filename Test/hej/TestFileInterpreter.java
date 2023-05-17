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
                    random_list = {
                        2 = { "Cheese" }
                        2 = {}
                    }
                }
                defence = 2
                damage = {
                   min = 2
                   max = 3
                }
            }""";
        ArrayList<String> objectsFound = FileInterpreter.findObjects(first, "UnitTest testParse of FileInterpreter", false);
        Object hashMap = FileInterpreter.parse(objectsFound.get(0), "UnitTest testParse of FileInterpreter");
        Assertions.assertEquals("small rat", HashMapExplorer.getString(hashMap, "name"));
        Assertions.assertEquals(2, HashMapExplorer.getNumber(hashMap, "defence"));
        Assertions.assertEquals(2, HashMapExplorer.getNumber(hashMap,"damage.min"));
    }

    @Test
    void testRegex(){
        final Pattern patternString = Pattern.compile("(?<whole>\\s*(?<key>\\w+)\\s*=\\s*\"(?<value>[^\"]*)\")");
        String thingToTest = "name = \"test rat\"";
        System.out.println(patternString.matcher(thingToTest).matches());
    }

    @Test
    void testsubstring(){
        String experiementText = " hello = {987654321}";
        int firstBracket = experiementText.indexOf('{');
        int endBracket = experiementText.lastIndexOf('}');
        System.out.println(experiementText.substring(firstBracket + 1, endBracket));
    }
}
