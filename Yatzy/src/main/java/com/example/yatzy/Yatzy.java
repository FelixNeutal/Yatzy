package com.example.yatzy;

import com.example.controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class Yatzy extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Yatzy.class.getResource("MainMenu.fxml"));
        //Call setController before load
        //remove fx.controller from fxml file otherwise new controller is created
        fxmlLoader.setController(new MainMenuController());
        stage.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Yatzy");
        stage.setScene(scene);
        stage.show();
        MainMenuController controller = fxmlLoader.getController();
        controller.setMainStage(stage);
    }

    public static void main(String[] args) {
        launch();
//        YatzyServer server = new YatzyServer(8000);
        //GameBrain brain = new GameBrain();
        //brain.soloGame();
    }
}