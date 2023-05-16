package GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import philosophers_ice.*;


public class AlertBox {

    static GameState gs;
    static boolean isClosed = true;

    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }


    public static GameState display(String title, String message, String typeOfInput, Scene currentScene){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);


        Label label = new Label(message);
        TextField input = new TextField(typeOfInput);
        input.setOnAction(e -> {
            gs = StateSaver.newGame(input.getText());
            isClosed = false;
            window.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,input);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return gs;
    }




}
