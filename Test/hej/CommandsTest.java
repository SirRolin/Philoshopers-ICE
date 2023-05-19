package hej;

import GUI.SharedData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import philosophers_ice.*;

public class CommandsTest {

    @BeforeAll
    static void loadData(){
        SharedData.gs = StateSaver.newGame("Test");
        Race.load();
        SharedData.gs.p1 = new Player("name", Race.getRaces().get(0), 50, 2, 10, 0, 0, 0);
        Commands.loadDefaults();
        Item.load();
        SharedData.gs.p1.inventory.addToItems(Item.getItem("knife"));
    }
    @Test
    void testInspect(){
        System.out.println(Commands.action("I inspect the knife"));
    }
}
