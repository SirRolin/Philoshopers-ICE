package hej;

import GUI.SharedData;
import org.junit.jupiter.api.Test;
import philosophers_ice.Currency;
import philosophers_ice.Item;
import philosophers_ice.Race;

public class launchTest {

    @Test
    void launchEnemies(){
        Item.load();
        SharedData.load();
        Currency.load();
        Race.load();
    }
}
