package philosophers_ice;

import GUI.SharedData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commands {

    private final ArrayList<Pattern> searchPattern = new ArrayList<>();
    private Function<Matcher, String> ACTION;

    public static ArrayList<Commands> commandsList = new ArrayList<Commands>();

    public static void loadDefaults() {
        if (!commandsList.isEmpty()) {
            return;
        }
        //// movement
        commandsList.add(new Commands("(go|walk|run)(?: to)? (?<input>\\w*)", (s) -> {
            GameState gameState = SharedData.gs;
            switch (s.group("input")) {
                case "north", "up", "n" -> {
                    System.out.println("I moved north");
                    gameState.y = gameState.y + 1;
                    SharedData.gsc.updateMap();
                    gameState.visitMap();
                    return "";
                }
                case "south", "down", "s" -> {
                    System.out.println("I moved south");
                    gameState.y = gameState.y - 1;
                    SharedData.gsc.updateMap();
                    gameState.visitMap();
                    return "";
                }
                case "west", "left", "w" -> {
                    System.out.println("I moved west");
                    gameState.x = gameState.x - 1;
                    SharedData.gsc.updateMap();
                    gameState.visitMap();
                    return "";
                }
                case "east", "right", "e" -> {
                    System.out.println("I moved east");
                    gameState.x = gameState.x + 1;
                    SharedData.gsc.updateMap();
                    gameState.visitMap();
                    return "";
                }
                default -> {
                    return "I don't get that Direction";
                }
            }
        }));
        //// look at item
        commandsList.add(new Commands("(inspect|x)(?: the)? (?<input>.*)", (s) -> {
            GameState gameState = SharedData.gs;
            for (Item item1 : gameState.p1.inventory.getItems()) {
                if (item1.getName().equals(s.group("input"))) {
                    return item1.description; //todo change to getDescription when that's implemented.
                }
            }
            return "I don't have that item";
        }));
        //// equip item
        commandsList.add(new Commands("(equip|e)(?: the)? (?<input>.*)", (s) -> {
            GameState gameState = SharedData.gs;
            Item item = gameState.p1.inventory.getItem(s.group("input"));
            if (item != null) {
                gameState.p1.inventory.equipItem(item); // todo return this when it's implemented.
                return "";
            }
            return "don't have an item like that";
        }));
        //// Test OutputStream
        commandsList.add(new Commands("test", (s) -> {
            for(int i = 0; i < 20; i++){
                System.out.println(i);
            }
            return "";
        }));
    }

    public Commands(String searchPatterns, Function<Matcher, String> ACTION) {
        this(new ArrayList<String>(List.of("(?:I )?(?:want to )?" + searchPatterns)), ACTION);
    }

    public Commands(ArrayList<String> searchPatterns, Function<Matcher, String> ACTION) {
        for (String s : searchPatterns) {
            this.searchPattern.add(Pattern.compile(s));
        }
        this.ACTION = ACTION;
    }

    public static String action(String text) {
        if(CombatScene.setInput(text)) return "";

        if(commandsList.isEmpty()) loadDefaults();
        for (Commands c : commandsList) {
            for (Pattern p : c.searchPattern) {
                Matcher m = p.matcher(text);
                if (m.find()) {
                    return c.ACTION.apply(m);
                }
            }
        }
        System.out.println("sorry we don't have an action like that.");
        return "";
    }
}
