package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import philosophers_ice.GameState;
import philosophers_ice.StateSaver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainMenuController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private GameState gs;


    public void switchToCharacterCreation(ActionEvent event) throws Exception{
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        gs = AlertBox.display("Name your savefile!", "Please name your savefile", "name");
        if(!AlertBox.isClosed) {
            SharedData.gs = gs;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("characterCreationMenu.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, screenBounds.getMaxX()/2,screenBounds.getMaxY()/2);
            stage.setScene(scene);
            stage.setTitle("Philosophers ICE - " + gs.name);
            stage.show();
        }
    }

    public void switchToLoadGame(ActionEvent event) throws Exception{
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("loadGame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, screenBounds.getMaxX()/2,screenBounds.getMaxY()/2);

        stage.setScene(scene);
        stage.show();
    }

    public void closeProgram() {
        // HER SKAL TING KØRES FOR AT GEMME
        if(gs != null){
            StateSaver.saveGame(gs);
        }
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
