package GUI;

import ICE.util.ErrorHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import philosophers_ice.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

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
    @FXML
    private GridPane map;
    @FXML
    GridPane inventoryPane;
    @FXML
    private DialogPane gamePane;
    @FXML
    private DialogPane logPane;
    @FXML
    private TextField chat;

    public void switchToMainMenu(ActionEvent event) throws Exception {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        SharedData.gs = null;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, screenBounds.getMaxX() / 2, screenBounds.getMaxY() / 2);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SharedData.gsc = this;
        gs = SharedData.gs;
        statsLabel.setText("Stats: " + gs.p1.name);

        QuitGame.setOnAction(e -> {
            StateSaver.saveGame(gs);
            System.exit(0);
        });

        ArrayList<Label> stats = new ArrayList<>();
        stats.add(new Label("Str: " + gs.p1.str));
        stats.add(new Label("Agi: " + gs.p1.agi));
        stats.add(new Label("Con: " + gs.p1.con));
        stats.add(new Label("Wits: " + gs.p1.wits));
        stats.add(new Label("WillPower: " + gs.p1.willPower));
        stats.add(new Label("Magi: " + gs.p1.magi));
        stats.add(new Label("Max HP: " + gs.p1.getMaxHP()));
        stats.add(new Label("Max MP: " + gs.p1.getMaxMP()));
        stats.add(new Label("Speed: " + gs.p1.getSpeed()));
        stats.add(new Label("Initiative: " + gs.p1.getMaxInitiative()));
        stats.add(new Label("Spellbuff: " + gs.p1.getSpellBuffProc() + "%"));


        for (int i = 0; i < stats.size(); i++) {
            statsPane.add(stats.get(i), i % 3, (int) i / 3);
        }

        int counter = 0;
        for(Item i: gs.p1.inventory.getItems()){
            inventoryPane.add(new Label (i.name), 1,counter);
            inventoryPane.add(new ImageView(i.getImage()),0,counter);
            counter++;
        }

        updateMap();


        //java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        PrintStreamWithLog out = new PrintStreamWithLog(System.out, logPane);
        System.setOut(out);
        InputStream in = new InputStreamFromTextField(chat);
        System.setIn(in);
        chat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Commands.action(chat.getText());
                chat.setText("");
            }
        });
    }


    public void startCombat(CombatScene combatScene){
        combatScene.startCombat();
    }

    public void updateMap(){
        map.getChildren().clear();
        MapTile[][] miniMap = SharedData.gs.getMinimap(9, 4);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                StackPane pos1 = new StackPane();
                if (miniMap[i][j] != null) {
                    Image[] images = miniMap[i][j].getImage();
                    if (images[0] != null) {
                        ImageView pos1Img1 = new ImageView(images[0]);
                        pos1.getChildren().add(pos1Img1);
                    }
                    if (images[1] != null) {
                        ImageView pos1Img2 = new ImageView(images[1]);
                        pos1.getChildren().add(pos1Img2);
                    }
                }
                if(i == 4 && j == 4){
                    ImageView playerImageView = new ImageView();
                    playerImageView.autosize();
                    playerImageView.setPreserveRatio(true);
                    playerImageView.setSmooth(false);
                    playerImageView.setX(playerImageView.getFitWidth()/2);
                    playerImageView.setFitWidth(32f);
                    playerImageView.setFitHeight(32f);
                    playerImageView.setImage(SharedData.gs.p1.getImage(32,32));
                    pos1.getChildren().add(playerImageView);
                }

                map.add(pos1, i, j);
            }
        }
    }

}




//    Scanner sc = new Scanner(System.in);