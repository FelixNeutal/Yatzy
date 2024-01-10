module com.example.yatzy {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.glassfish.tyrus.server;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.yatzy to javafx.fxml;
    opens com.example.game to javafx.fxml;
    exports com.example.yatzy;
    opens com.example.controller to javafx.fxml;
    exports com.example.controller;
}