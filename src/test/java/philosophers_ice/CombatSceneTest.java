package philosophers_ice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CombatSceneTest {
    CombatScene scene1;
    @BeforeEach
    void setUp() {
        Player p1 = new Player("Patrick",10, 10, 10,10,10,10,100,10);
        ArrayList<Enemy> enemies = new ArrayList<>();
        Enemy e1 = new Enemy("Rat","path" ,"its brown! ARGH", 25,100,25 ,5);
        enemies.add(e1);
        scene1 = new CombatScene(p1,enemies);
    }

    @Test
    void startCombat() {
        scene1.startCombat();
    }

    @Test
    void combat() {
    }

    @Test
    void endCombat() {
    }

    @Test
    void endGame() {
    }
}