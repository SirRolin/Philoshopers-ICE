<<<<<<<< Updated upstream:test/java/util/CombatSceneTest.java
package hej;
========
package java.philosophers_ice;
>>>>>>>> Stashed changes:test/java/philosophers_ice/CombatSceneTest.java

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import philosophers_ice.CombatScene;
import philosophers_ice.Enemy;
import philosophers_ice.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CombatSceneTest {
    CombatScene scene1;
    @BeforeEach
    void setUp() {
<<<<<<<< Updated upstream:test/java/util/CombatSceneTest.java
        Player p1 = new Player("Patrick","Human",10, 10, 10,10,10,10,100,10);
========
        Player p1 = new Player("Patrick", "Human",10, 10, 10,10,10,10,10,10);
>>>>>>>> Stashed changes:test/java/philosophers_ice/CombatSceneTest.java
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