package philosophers_ice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Commands {

    private ArrayList<String> searchPattern = new ArrayList<>();
    private final Runnable ACTION;

    public static ArrayList<Commands> commandsList;

    public static void loadDefaults(){
        commandsList.add(new Commands("(go|walk|run)(?: to)? (?<place>\\w*)", () -> {}));
        commandsList.add(new Commands("(inspect|x)(?: the)? (?<item>.*)", () -> {}));
        commandsList.add(new Commands("(equip|e)(?: the)? (?<item>.*)", () -> {}));
    }

    public Commands(String searchPatterns, Runnable ACTION){
        this((ArrayList<String>) List.of("(?:I )?(?:want to )?" + searchPatterns), ACTION);
    }
    public Commands(ArrayList<String> searchPatterns, Runnable ACTION){
        this.searchPattern = searchPatterns;
        this.ACTION = ACTION;
    }

    public void action(String text){

    }



    public void actionNotUsed(String text) {
        GameState gameState = new GameState("action");
        if (text == "go") {
            System.out.println("which direction do you want to go to, north,south,east,west?");
            Scanner scanner = new Scanner(System.in);
            //scanner.nextLine(); might be buggy if this stays
            String choice = scanner.nextLine();
            if (scanner.hasNextLine()) {

                switch (choice) {
                    case "north":
                        gameState.y = gameState.y + 1;
                        break;
                    case "south":
                        gameState.y = gameState.y - 1;
                        break;
                    case "west":
                        gameState.x = gameState.x - 1;
                        break;
                    case "east":
                        gameState.x = gameState.x + 1;
                    default:
                        System.out.println("wrong action please type a new command again");
                        choice = scanner.nextLine();
                        action(choice);
                        break;
                }
            }
        } else if (text == "inspect" || text == "x") {
            System.out.println("Which item in your inventory do you want to look at?");
            Scanner scanner = new Scanner(System.in);

            String item = scanner.nextLine();
            for (Item item1 : gameState.p1.inventory.getItems()) {
                if (item1.getName() == item) {
                    System.out.println(item1.description);
                }
            }



        } else if (text == "equip") {
            System.out.println("what do you want to equip?");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            if (scanner.hasNextLine()) {
                String choice = scanner.nextLine();
                Item item = gameState.p1.inventory.getItem(choice);
                gameState.p1.inventory.equipItem(item);

            }
        }
    }
    public void search(String text){
        GameState gameState = new GameState("searchMe");
        if(commandsList.contains(text)){
            action(text);
        }
    }


}
