package GUI;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import philosophers_ice.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class Main extends Application {

    Stage window;
    Scene mainMenu;
    Scene characterCreation;
    Scene loadGame;
    Scene gameScene;
    Scene fightSequence;


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        primaryStage.setTitle("Philosophers ICE");
        primaryStage.setScene(new Scene(root,screenBounds.getMaxX()/2,screenBounds.getMaxY()/2));
        primaryStage.show();

        /*
        window = primaryStage;
        //Getting screensize


        //Setting the window title
        window.setTitle("Philosophers ICE");

        window.setOnCloseRequest(e -> closeProgram());


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
        //mainButtons[0].setOnAction(e -> {alert.show();});
        mainButtons[0].setOnAction(e -> {gs = AlertBox.display("Creating save file", "Please enter the name of your save", "name"); window.setScene(characterCreation);});
        mainButtons[1].setOnAction(e -> window.setScene(loadGame));
        mainButtons[2].setOnAction(e -> closeProgram());



        //sets the scenes:
        //Mainmenu:
        mainMenu = new Scene(menuLayout, screenBounds.getMaxX()/2, screenBounds.getMaxY()/2);
        window.setScene(mainMenu);
        window.setFullScreenExitHint("");
        window.setMaximized(true);
        window.setWidth(screenBounds.getWidth());
        window.setHeight(screenBounds.getHeight());
        window.show();

//---------------------------------------------------------------------------------------------------------------------------------

        //CHARACTER CREATION:
        TilePane characterCreationLayout = new TilePane();
        characterCreationLayout.setOrientation(Orientation.VERTICAL);
        characterCreationLayout.setAlignment(Pos.CENTER);
        characterCreationLayout.setPrefRows(4);
        Label gameState = new Label(gs.name);


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

        characterCreationLayout.getChildren().addAll(inputName, gameState);
        characterCreation = new Scene(characterCreationLayout, screenBounds.getMaxX(), screenBounds.getMaxX());

         */
    }

    private void closeProgram() {
        // HER SKAL TING KØRES FOR AT GEMME
        window.close();
    }
}



