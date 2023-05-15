package hej;

import ICE.util.FileInterpreter;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testFileInterpreter {
    @Test
    void testSplitObjects(){
        String expected = """
                rat = {
                           name = "small rat"
                           imagePath = "Small Rat.png"
                           description = "a small rat"
                           defence = 2
                           damage = 2
                }""";
        String test = """
                }
                rat = {
                           name = "small rat"
                           imagePath = "Small Rat.png"
                           description = "a small rat"
                           defence = 2
                           damage = 2
                }""";

        StringBuilder sb = new StringBuilder();
        String[] arr = test.split("\n");
        ArrayList<String> lis = new ArrayList<String>(Arrays.asList(arr));
        ArrayList<String> split = FileInterpreter.splitObjects(lis,"tester");
        for(String s: split){
            sb.append(s).append("\n");
        }
        assertEquals(expected, sb.toString());
    }
}
