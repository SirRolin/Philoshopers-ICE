package GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CharacterCreationController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList<File> files = new ArrayList<>();
    private ArrayList<Image> images= new ArrayList<>();
    @FXML
    private Button startButton;
    @FXML
    private ImageView raceImage;
    @FXML
    private Image currentRaceImage;

    public void switchToMainMenu(ActionEvent event) throws Exception{
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, screenBounds.getMaxX()/2,screenBounds.getMaxY()/2);
        stage.setScene(scene);
        stage.setTitle("Philosophers ICE");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("Data/gfx/Races/Humanfixedmore.png");
        if(file.exists()) {
            currentRaceImage = new Image(file.toURI().toString());
        }else {
            currentRaceImage = new Image("_NULL_.png");
        }
        raceImage.setImage(currentRaceImage);
        raceImage.setScaleX(2.0);
        raceImage.setScaleY(2.0);
        raceImage.setSmooth(false);
    }

    public void onStartButton(){
        File file = new File("Data/gfx/Races/Orgefixed.png");
        System.out.println(file.exists());
        currentRaceImage =  new Image(file.toURI().toString());
        raceImage.setImage(currentRaceImage);
        System.out.println(currentRaceImage.getUrl());

    }
}
