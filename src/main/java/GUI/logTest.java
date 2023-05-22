package GUI;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import philosophers_ice.*;

import java.util.ArrayList;
import java.util.List;

public class logTest extends Application {

    Stage window;
    Scene mainMenu;
    Scene characterCreation;
    Scene loadGame;
    Scene gameScene;
    Scene fightSequence;
    public static void main(String[] args){
        //String[] args = {};
        SharedData.gs = StateSaver.newGame("test");
        Race.load();
        SharedData.gs.p1 = new Player("test", Race.getRace("human"),5,5,5,5,5,5);
        //SharedData.gs.getMinimap(1,0)[0][0].enemies = (ArrayList<Enemy>) List.of(new Enemy("lars",null,"",5,5,5,5);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GameScene.fxml"));
        primaryStage.setTitle("Philosophers ICE");
        primaryStage.setScene(new Scene(root, screenBounds.getMaxX() / 2, screenBounds.getMaxY() / 2));
        primaryStage.show();
    }

}
