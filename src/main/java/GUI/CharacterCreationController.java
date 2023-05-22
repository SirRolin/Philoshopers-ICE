package GUI;

import ICE.util.FileInterpreter;
import ICE.util.HashMapExplorer;
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
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import philosophers_ice.GameState;
import philosophers_ice.Player;
import philosophers_ice.StateSaver;
import philosophers_ice.*;
import ICE.util.ErrorHandler;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class CharacterCreationController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Player currentPlayer;

    private String playerName;
    private Race playerRace;

    private ArrayList<Label> statLabels = new ArrayList<>();
    private ArrayList<Button> addButtons = new ArrayList<>();
    private ArrayList<Button> subtractButtons = new ArrayList<>();
    private ArrayList<Race> races;
    private int raceIndex;
    private int[] stats = {
            0,
            0,
            0,
            0,
            0,
            0
    };
    private String[] statNames = {
            "Str: ",
            "Agi: ",
            "Con: ",
            "Wits: ",
            "Willpower: ",
            "Magi: "
    };


    public GameState gs;
    @FXML
    private Button startButton;
    @FXML
    private DialogPane raceBio;
    @FXML
    private ImageView raceImage;
    @FXML
    private GridPane statPane;
    @FXML
    private TextField nameField;
    @FXML
    private Button leftRaceButton;
    @FXML
    private Button rightRaceButton;

    public void switchToMainMenu(ActionEvent event) throws Exception {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        SharedData.gs = null;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, screenBounds.getMaxX() / 2, screenBounds.getMaxY() / 2);
        stage.setScene(scene);
        stage.setTitle("Philosophers ICE");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Currency.load();
        Race.load();
        SharedData.load();
        String defaultRace = SharedData.getDefineString("defaultRace");
        races = Race.getRaces();

        if (SharedData.gs != null) {
            //PLAYER HAS USERDATA
            gs = SharedData.gs;
            SharedData.gs = null;
            if (gs.p1 != null) {
                currentPlayer = gs.p1;
//                raceImage.setScaleX(2.0);
//                raceImage.setScaleY(2.0);
                raceImage.setPreserveRatio(true);
                raceImage.setSmooth(false);
                raceImage.setFitHeight(256);
                raceImage.setImage(gs.p1.getImage(256,256));
                playerName = currentPlayer.name;
                playerRace = currentPlayer.race;
                nameField.setText(currentPlayer.name);

            } else {
                //PLAYER DOES NOT HAVE USERDATA
                if (defaultRace != null) {
                    raceIndex = Race.getIndexOf(races, defaultRace);
                } else {
                    raceIndex = 0;
                }
                gs.p1 = new Player("name", races.get(raceIndex), 0, 0, 0, 0, 0, 0);
                gs.p1.inventory.addCurrency("Life Essence", 30);
                gs.p1.inventory.addToItems(Item.getItem("knife"));
                gs.p1.inventory.addToItems(Item.getItem("cheese"));
                currentPlayer = gs.p1;
                playerName = currentPlayer.name;
                playerRace = currentPlayer.race;
                raceImage.setImage(currentPlayer.getImage(256,256));
                raceImage.setScaleX(2.0);
                raceImage.setScaleY(2.0);
                raceImage.setSmooth(false);

            }
            raceBio.setContentText(races.get(raceIndex).bio);

            for (int i = 0; i < statNames.length; i++) {
                Button subtractBtn = new Button("-");
                ;
                subtractBtn.setAlignment(Pos.CENTER);
                Button plusBtn = new Button("+");
                plusBtn.setAlignment(Pos.CENTER);
                Label statLabel = new Label(statNames[i] + gs.p1.getStatFromIndex(i) + "(" + gs.p1.getDerivedStat(i) + ")");
                statLabel.setAlignment(Pos.CENTER);
                statLabels.add(statLabel);
                addButtons.add(plusBtn);
                subtractButtons.add(subtractBtn);

                setUpSbtButton(i);
                setUpAddButton(i);

                statPane.add(subtractBtn, 0, i);
                statPane.add(statLabel, 1, i);
                statPane.add(plusBtn, 2, i);
            }

            leftRaceButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    raceIndex -= 1;
                    if (raceIndex < 0) {
                        raceIndex = races.size() - 1;
                    }
                    raceImage.setImage(races.get(raceIndex).getImage(256,256));
                    raceBio.setContentText(races.get(raceIndex).bio);
                    System.out.println(races.get(raceIndex).name);
                }
            });

            rightRaceButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    raceIndex += 1;
                    if (raceIndex > races.size() - 1) {
                        raceIndex = 0;
                    }
                    raceImage.setImage(races.get(raceIndex).getImage(256,256));
                    raceBio.setContentText(races.get(raceIndex).bio);
                    System.out.println(races.get(raceIndex).name);
                }
            });

        } else {
            ErrorHandler.handleError(new Exception("No savefile found! How did you get here?"));
        }
    }

    public void onStartButton() {
        currentPlayer.name = nameField.getText();
        currentPlayer.race = races.get(raceIndex);
        gs.p1 = currentPlayer;
        StateSaver.saveGame(gs);
        System.out.println(raceImage.getImage().getUrl());

    }

    private void setUpSbtButton(final int index) {
        subtractButtons.get(index).setOnAction(e -> {
            if (gs.p1.getStatFromIndex(index) != -1 && gs.p1.inventory.getCurrency("Life Essence") != null) {
                gs.p1.setStatFromIndex(index, gs.p1.getStatFromIndex(index) - 1);
                statLabels.get(index).setText(statNames[index] + gs.p1.getStatFromIndex(index) + "(" + gs.p1.getDerivedStat(index) + ")");
                gs.p1.inventory.addCurrency("Life Essence",1);
            }
        });
    }

    private void setUpAddButton(final int index) {
        addButtons.get(index).setOnAction(e -> {
            if (gs.p1.inventory.getCurrency("Life Essence") != null && gs.p1.inventory.getCurrency("Life Essence").amount > 0) {
                int value = (int) gs.p1.getStatFromIndex(index) + 1;
                gs.p1.setStatFromIndex(index, value);
                statLabels.get(index).setText(statNames[index] + gs.p1.getStatFromIndex(index) + "(" + gs.p1.getDerivedStat(index) + ")");
                gs.p1.inventory.addCurrency("Life Essence",-1);
            }
        });
    }


}
