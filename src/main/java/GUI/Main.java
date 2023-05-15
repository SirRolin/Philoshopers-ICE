package GUI;

import com.almasb.fxgl.core.collection.grid.Grid;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;


public class Main extends Application {

    Scene mainMenu;
    Scene characterCreation;
    Scene loadGame;
    Scene gameScene;
    Scene fightSequence;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        //Getting screensize
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        //Setting the window title
        stage.setTitle("Philosophers ICE");


        //MAIN MENU SETUP
        //Setting the layout for the window
        TilePane menuLayout = new TilePane();
        menuLayout.setOrientation(Orientation.VERTICAL);
        menuLayout.setPrefRows(4);
        menuLayout.setAlignment(Pos.CENTER);

        //Settings the title and font of the game
        Text title = new Text("Philosophers ICE");
        title.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 50));

        //Adds the title to the screen
        menuLayout.getChildren().add(title);

        //List of all the buttons available on the screen
        Button[] mainButtons = new Button[]{
                new Button("Start New Game"),
                new Button("Load Game"),
                new Button("End Game"),
        };

        //Adds the buttons
        for(Button b : mainButtons){
            menuLayout.getChildren().add(b);
        }

        //Sets functions to buttons:
        mainButtons[0].setOnAction(e -> stage.setScene(characterCreation));
        mainButtons[1].setOnAction(e -> stage.setScene(loadGame));
        mainButtons[2].setOnAction(e -> stage.close());



        //sets the scenes:
        //Mainmenu:
        mainMenu = new Scene(menuLayout, screenBounds.getMaxX()/2, screenBounds.getMaxY()/2);
        stage.setScene(mainMenu);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();

        //CHARACTER CREATION:



        //characterCreation = new Scene();

    }
}



