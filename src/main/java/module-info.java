module com.example.javafxgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires annotations;

    opens GUI to javafx.fxml;
    exports GUI;
}