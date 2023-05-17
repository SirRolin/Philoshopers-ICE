package philosophers_ice;

import java.util.ArrayList;
import java.util.Scanner;

public class Commands {

    private String searchPatterns;

    public Commands(String searchPatterns){

    }


    public void action(String text){
        GameState gameState = new GameState("action");
        if(text == "go to"){
            System.out.println("which direction do you want to go to?");
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextLine()){
                String choice = scanner.nextLine();
                switch (choice){
                    case "north":
                        gameState.y = gameState.y+1;
                        break;
                    case "south":
                        gameState.y = gameState.y-1;
                        break;
                    case "west":
                        gameState.x = gameState.x-1;
                        break;
                    case "east":
                        gameState.x = gameState.x+1;
                    default:
                        action(text);
                        break;
                }
            }
        } else if (text == "inspect" || text == "x") {
            System.out.println("Which item in your inventory do you want to look at?");
            Scanner scanner = new Scanner(System.in);
            String item = scanner.nextLine();
            for(Item item1 : gameState.p1.inventory.getItems()){
                if(item1.getName() == item){
                    System.out.println(item1.description);
                }
            }

            //if(gameState.p1.inventory.getItems().contains()){}

        }
    }

    public void search(String text){
        GameState gameState = new GameState("searchMe");
        if(gameState.commandsList.contains(text)){
            action(text);
        }

    }


}
