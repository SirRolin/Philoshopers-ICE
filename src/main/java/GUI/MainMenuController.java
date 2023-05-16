package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import philosophers_ice.GameState;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private GameState gs;

    public void switchToMainMenu(ActionEvent event) throws Exception{
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, screenBounds.getMaxX()/2,screenBounds.getMaxY()/2);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCharacterCreation(ActionEvent event) throws Exception{
        gs = AlertBox.display("Name your savefile!", "Please name your savefile", "name");
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("characterCreationMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, screenBounds.getMaxX()/2,screenBounds.getMaxY()/2);
        stage.setScene(scene);
        stage.show();
    }

    public void closeProgram() {
        // HER SKAL TING KØRES FOR AT GEMME
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
