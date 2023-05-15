package GUI;

import com.almasb.fxgl.core.collection.grid.Grid;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import philosophers_ice.*;
import javafx.stage.Window;
import javafx.stage.PopupWindow;
import javafx.stage.Popup;


public class Main extends Application {

    Scene mainMenu;
    Scene characterCreation;
    Scene loadGame;
    Scene gameScene;
    Scene fightSequence;

    Scene saveName;
    Stage alert;
    GameState gs;


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Stage primaryStage = stage;

        //Popup creation
        TilePane saveNameLayout = new TilePane();
        Label label = new Label("Enter your save name:");
        TextField saveNameField = new TextField();
        //Button enterButton = new Button("ENTER");
        saveNameLayout.getChildren().addAll(label,saveNameField);
        saveNameLayout.setAlignment(Pos.CENTER);
        saveNameLayout.setOrientation(Orientation.HORIZONTAL);
        saveNameLayout.setPrefRows(1);


        alert = new Stage();
        saveNameField.setOnAction(e -> {gs = StateSaver.newGame(saveNameField.getCharacters().toString()); primaryStage.setScene(characterCreation); alert.close();});
        saveName = new Scene(saveNameLayout, 200,100);
        alert.setScene(saveName);






        //Getting screensize
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        //Setting the window title
        primaryStage.setTitle("Philosophers ICE");


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
        mainButtons[0].setOnAction(e -> {alert.show();});
        mainButtons[1].setOnAction(e -> primaryStage.setScene(loadGame));
        mainButtons[2].setOnAction(e -> primaryStage.close());



        //sets the scenes:
        //Mainmenu:
        mainMenu = new Scene(menuLayout, screenBounds.getMaxX()/2, screenBounds.getMaxY()/2);
        primaryStage.setScene(mainMenu);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setMaximized(true);
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.show();
//---------------------------------------------------------------------------------------------------------------------------------

        //CHARACTER CREATION:
        TilePane characterCreationLayout = new TilePane();
        characterCreationLayout.setOrientation(Orientation.VERTICAL);
        characterCreationLayout.setAlignment(Pos.CENTER);
        characterCreationLayout.setPrefRows(4);

        Button[] characterCreationButtons = new Button[]{
                new Button("Start"),
                new Button("<--"),
                new Button("<-"),
                new Button("->"),
                new Button("-1"),
                new Button("+1"),
                new Button("Enter"),
        };

        TextField inputName = new TextField("Character name");

        for(Button b : characterCreationButtons){
            characterCreationLayout.getChildren().add(b);
        }

        characterCreationLayout.getChildren().add(inputName);
        characterCreation = new Scene(characterCreationLayout, screenBounds.getMaxX(), screenBounds.getMaxX());
    }
}



