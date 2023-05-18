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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import philosophers_ice.GameState;
import philosophers_ice.MapTile;
import philosophers_ice.StateSaver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private GameState gs;

    @FXML
    private Button MainMenu;
    @FXML
    private Button QuitGame;
    @FXML
    private Label statsLabel;
    @FXML
    private GridPane statsPane;
    @FXML GridPane map;

    public void switchToMainMenu(ActionEvent event) throws Exception{
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        SharedData.gs = null;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, screenBounds.getMaxX()/2,screenBounds.getMaxY()/2);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gs = SharedData.gs;
        statsLabel.setText("Stats: "+gs.p1.name);

        QuitGame.setOnAction(e -> {
            StateSaver.saveGame(gs);
            System.exit(0);
        });

        ArrayList<Label> stats = new ArrayList<>();
        stats.add(new Label("Str: "+gs.p1.str));
        stats.add(new Label("Agi: "+gs.p1.agi));
        stats.add(new Label("Con: "+gs.p1.con));
        stats.add(new Label("Wits: "+gs.p1.wits));
        stats.add(new Label("WillPower: "+gs.p1.willPower));
        stats.add(new Label("Magi: "+gs.p1.magi));
        stats.add(new Label("Max HP: "+gs.p1.getMaxHP()));
        stats.add(new Label("Max MP: "+gs.p1.getMaxMP()));
        stats.add(new Label("Speed: "+gs.p1.getSpeed()));
        stats.add(new Label("Initiative: "+gs.p1.getInitiative()));
        stats.add(new Label("Spellbuff: "+gs.p1.getSpellBuffProc()+"%"));


        for(int i = 0; i < stats.size(); i++){
            statsPane.add(stats.get(i),i%3,(int)i/3);
        }

        MapTile[][] miniMap = gs.getMinimap(5);
        StackPane pos1 = new StackPane();
        ImageView pos1Img1 = new ImageView(miniMap[4][4].getImage()[0]);
        ImageView pos1Img2 = new ImageView(miniMap[4][4].getImage()[1]);
        pos1.getChildren().add(pos1Img1);
        pos1.getChildren().add(pos1Img2);
        map.add(pos1,4,4);


    }
}
