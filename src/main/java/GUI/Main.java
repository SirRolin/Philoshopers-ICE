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

    }

    /*
    private void closeProgram() {
        // HER SKAL TING KØRES FOR AT GEMME, IKKE SIKKERT VI SKAL BRUGE DEN ALLIGVEL
        window.close();
    }
    */
}



