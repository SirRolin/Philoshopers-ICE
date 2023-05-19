package philosophers_ice;

import GUI.SharedData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commands {

    private final ArrayList<Pattern> searchPattern = new ArrayList<>();
    private Function<String, String> ACTION;

    public static ArrayList<Commands> commandsList;

    public static void loadDefaults() {
        commandsList.add(new Commands("(go|walk|run)(?: to)? (?<input>\\w*)", (s) -> {
            GameState gameState = SharedData.gs;
            switch (s) {
                case "north", "up", "n" -> {
                    gameState.y = gameState.y + 1;
                    return "I moved north";
                }
                case "south", "down", "s" -> {
                    gameState.y = gameState.y - 1;
                    return "I moved south";
                }
                case "west", "left", "w" -> {
                    gameState.x = gameState.x - 1;
                    return "I moved west";
                }
                case "east", "right", "e" -> {
                    gameState.x = gameState.x + 1;
                    return "I moved east";
                }
                default -> {
                    return "I don't get that Dirrection";
                }

            }
        }));
        commandsList.add(new Commands("(inspect|x)(?: the)? (?<input>.*)", (s) -> {
            GameState gameState = SharedData.gs;
            for (Item item1 : gameState.p1.inventory.getItems()) {
                if (item1.getName().equals(s)) {
                    return item1.description; //todo change to getDescription when that's implemented.
                }
            }
            return "I don't have that item";
        }));
        commandsList.add(new Commands("(equip|e)(?: the)? (?<input>.*)", (s) -> {
            GameState gameState = SharedData.gs;
            Item item = gameState.p1.inventory.getItem(s);
            if(item != null){
                gameState.p1.inventory.equipItem(item); // todo return this when it's implemented.
                return "";
            }
            return "";
        }));
    }

    public Commands(String searchPatterns, Function<String, String> ACTION) {
        this((ArrayList<String>) List.of("(?:I )?(?:want to )?" + searchPatterns), ACTION);
    }

    public Commands(ArrayList<String> searchPatterns, Function<String, String> ACTION) {
        for (String s : searchPatterns) {
            this.searchPattern.add(Pattern.compile(s));
        }
        this.ACTION = ACTION;
    }

    public String action(String text) {
        for (Pattern p : searchPattern) {
            Matcher m = p.matcher(text);
            if (m.find()) {
                return ACTION.apply((String) m.group("input"));
            }
        }
        return "sorry we don't have an action like that.";
    }


}
