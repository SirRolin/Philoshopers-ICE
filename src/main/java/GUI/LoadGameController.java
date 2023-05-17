package GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import philosophers_ice.StateSaver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadGameController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    ArrayList<Button> buttons;
    ArrayList<Label> saveNames;
    @FXML
    Button saveButton1;
    Button saveButton2;
    Button saveButton3;
    Button saveButton4;
    Button saveButton5;
    Button saveButton6;
    Button saveButton7;
    Button saveButton8;
    Button saveButton9;
    Button saveButton10;
    Button saveButton11;
    Label saveName1 = new Label();
    Label saveName2;
    Label saveName3;
    Label saveName4;
    Label saveName5;
    Label saveName6;
    Label saveName7;
    Label saveName8;
    Label saveName9;
    Label saveName10;
    Label saveName11;

    public void switchToMainMenu(ActionEvent event) throws Exception{
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, screenBounds.getMaxX()/2,screenBounds.getMaxY()/2);
        stage.setScene(scene);
        stage.setTitle("Philosophers ICE");
        stage.show();
    }



    public void loadGameButton(ActionEvent event){
        if(event.getSource() == saveButton1){
            StateSaver.loadGame(saveName1.getText());

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
