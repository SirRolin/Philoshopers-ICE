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
    private ImageView raceImage;
    @FXML
    private Image currentRaceImage;
    @FXML
    private GridPane statPane;
    @FXML
    private TextField nameField;
    @FXML
    private Button leftRaceButton;
    @FXML
    private Button rightRaceButton;

    public void switchToMainMenu(ActionEvent event) throws Exception{
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        SharedData.gs = null;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, screenBounds.getMaxX()/2,screenBounds.getMaxY()/2);
        stage.setScene(scene);
        stage.setTitle("Philosophers ICE");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Race.load();
        SharedData.load();
        String defaultRace = HashMapExplorer.getString(SharedData.defines,"DefaultRace");
        races = Race.getRaces();

        if(SharedData.gs != null) {
            //PLAYER HAS USERDATA
            gs = SharedData.gs;
            SharedData.gs = null;
            if(gs.p1 != null) {
                currentPlayer = gs.p1;
                currentRaceImage = gs.p1.getImage();
                raceImage.setImage(currentRaceImage);
                raceImage.setScaleX(2.0);
                raceImage.setScaleY(2.0);
                raceImage.setSmooth(false);
                stats[0] = currentPlayer.str;
                stats[1] = currentPlayer.agi;
                stats[2] = currentPlayer.con;
                stats[3] = currentPlayer.wits;
                stats[4] = currentPlayer.willPower;
                stats[5] = currentPlayer.magi;
                playerName = currentPlayer.name;
                playerRace = currentPlayer.race;
                nameField.setText(currentPlayer.name);

            }else {
                //PLAYER DOES NOT HAVE USERDATA
                if(defaultRace != null){
                    raceIndex = Race.getIndexOf(races, defaultRace);
                }
                gs.p1 = new Player("name",Race.getRace(defaultRace),0,0,0,0,0,0);
                currentPlayer = gs.p1;
                playerName = currentPlayer.name;
                playerRace = currentPlayer.race;
                currentRaceImage = currentPlayer.getImage();
                raceImage.setImage(currentRaceImage);
                raceImage.setScaleX(2.0);
                raceImage.setScaleY(2.0);
                raceImage.setSmooth(false);
<<<<<<< Updated upstream
                /*
                ArrayList<HashMap<?, ?>> races = new ArrayList<>();
                for(Object obj: FileInterpreter.parseFolder("Data/common/Races/")){
                    if(obj instanceof HashMap<?, ?> map){
                        races.add(map);
                    }
                }
                gs.p1 = new Player("name", new Race(races.get(0)),0,0,0,0,0,0,0,0);
                */
                gs.p1 = new Player("name", new Race("human", null),0,0,0,0,0,0);

=======
>>>>>>> Stashed changes
            }

            for(int i = 0; i < statNames.length; i++){
                Button subtractBtn = new Button("-");;
                subtractBtn.setAlignment(Pos.CENTER);
                Button plusBtn = new Button("+");
                plusBtn.setAlignment(Pos.CENTER);
                Label statLabel = new Label(statNames[i]+stats[i]);
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
                    if(raceIndex < 0){
                        raceIndex = races.size();
                    }
                    System.out.println(raceIndex);
                }
            });

            rightRaceButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    raceIndex += 1;
                    if(raceIndex > races.size()){
                        raceIndex = 0;

                    }
                    System.out.println(raceIndex);
                }
            });

        }else {
            ErrorHandler.handleError(new Exception("No savefile found! How did you get here?"));
        }
    }

    public void onStartButton(){
        //File file = new File("Data/gfx/Races/Orgefixed.png");
        currentPlayer.str = stats[0];
        currentPlayer.agi = stats[1];
        currentPlayer.con = stats[2];
        currentPlayer.wits = stats[3];
        currentPlayer.willPower = stats[4];
        currentPlayer.magi = stats[5];
        currentPlayer.name = nameField.getText();
        StateSaver.saveGame(gs);
        //System.out.println(file.exists());
        //currentRaceImage =  new Image(file.toURI().toString());
        raceImage.setImage(currentRaceImage);
        System.out.println(currentRaceImage.getUrl());

    }

    private void setUpSbtButton(final int index){
        subtractButtons.get(index).setOnAction(e -> {
            if(stats[index] != 0 && gs.p1.inventory.getCurrency("Life Essence") != null){
                stats[index] -=1;
                statLabels.get(index).setText(statNames[index]+stats[index]);
                gs.p1.inventory.getCurrency("Life Essence").amount += 1;
            }});
    }

    private void setUpAddButton(final int index){
        addButtons.get(index).setOnAction(e -> {
            if(gs.p1.inventory.getCurrency("Life Essence") != null && gs.p1.inventory.getCurrency("Life Essence").amount > 0){
                stats[index] +=1;
                statLabels.get(index).setText(statNames[index]+stats[index]);
                gs.p1.inventory.getCurrency("Life Essence").amount -= 1;
            }});
    }


}
