package GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import philosophers_ice.GameState;
import philosophers_ice.StateSaver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoadGameController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private GameState gs;
    @FXML
    GridPane testGrid;

    public void switchToMainMenu(ActionEvent event) throws Exception{
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, screenBounds.getMaxX()/2,screenBounds.getMaxY()/2);
        stage.setScene(scene);
        stage.setTitle("Philosophers ICE");
        stage.show();
    }

    public void switchToGameScene(ActionEvent event) throws Exception{
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("GameScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, screenBounds.getMaxX()/2,screenBounds.getMaxY()/2);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File[] directories = new File("Data/saves/").listFiles(File::isDirectory);
        testGrid.setAlignment(Pos.TOP_CENTER);
        ArrayList<Button> buttons = new ArrayList<>();
        ArrayList<Label> labels = new ArrayList<>();
        if(directories != null) {
            int count = 0;
            for (File f : directories) {
                Button button = new Button("Load");
                button.autosize();
                button.setStyle("-fx-font-size: 20");
                button.setAlignment(Pos.CENTER);
                Label label = new Label(f.getName());
                label.setStyle("-fx-font-size: 20");
                label.setAlignment(Pos.CENTER);
                buttons.add(button);
                labels.add(label);
                testGrid.add(label, 0, count);
                testGrid.add(button, 1, count);
                count++;
            }

            for(int i = 0; i < buttons.size(); i++){
                final int num = i;
                buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        gs = StateSaver.loadGame(labels.get(num).getText());
                        System.out.println(gs.name);
                        SharedData.gs = gs;
                        System.out.println(SharedData.gs.name);
                        try {
                            switchToGameScene(actionEvent);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }

        }
    }
}
