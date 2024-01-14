package com.example.controller;

import com.example.game.BotGame;
import com.example.game.Game;
import com.example.game.PvPGame;
import com.example.game.SoloGame;
import com.example.player.Player;
import com.example.yatzy.Yatzy;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class MainMenuController implements Controller{
//    public int foobar = 0;
    @FXML
    private Button soloGameButton;
    @FXML
    private Button pvpButton;
    @FXML
    private Button hotSeatButton;
    @FXML
    private Button vsBotButton;
    private Stage mainStage = null;
    private Game currentGame;

    @FXML
    public void initialize() {
//        System.out.println("Initializing controller");
        soloGameButton.getStyleClass().setAll("btn","btn-success");
        pvpButton.getStyleClass().setAll("btn","btn-success");
        hotSeatButton.getStyleClass().setAll("btn","btn-success");
        vsBotButton.getStyleClass().setAll("btn","btn-success");
    }

    @FXML
    protected void onSoloGameClick() throws IOException {
        if (mainStage == null) return;
        FXMLLoader loader = switchScene("GameScreen.fxml", new SoloGameController());
        SoloGameController controller = loader.getController();
        controller.setMainController(this);
        Player p = new Player("Felix");
        currentGame = new SoloGame();
        controller.setGame(currentGame);
    }
    @FXML
    protected void onPvPGameClick() throws IOException {
        if (mainStage == null) return;
        FXMLLoader loader = switchScene("GameScreen.fxml", new PvPGameController());
        PvPGameController controller = loader.getController();
        controller.setMainController(this);
        Player p = new Player("Felix");
        currentGame = new PvPGame();
        controller.setGame(currentGame);
        controller.initializeConnection();
    }
    @FXML
    protected void onHotSeatGameClick() {}
    @FXML
    protected void onVSBotGameClick() throws IOException {
        if (mainStage == null) return;
        FXMLLoader loader = switchScene("GameScreen.fxml", new BotGameController());
        BotGameController controller = loader.getController();
        controller.setMainController(this);
        Player p = new Player("Felix");
        currentGame = new BotGame();
        controller.setGame(currentGame);
    }

    public void endCurrentGame() throws IOException {
        FXMLLoader loader = switchScene("GameEnd.fxml", null);
        GameEndController controller = loader.getController();
        controller.setGame(currentGame);
        controller.setGameEndInfo();
        controller.setMainController(this);
        //currentGame.finalize();
//        System.out.println("endCurrentGame foobar is: " + foobar);
    }

    public void backToMainMenu() throws IOException {
        FXMLLoader loader = switchScene("MainMenu.fxml", this);
        //currentGame.finalize();
//        System.out.println("Is stage null: " + (mainStage == null));
//        loader.setController(this);
    }

    public void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public FXMLLoader switchScene(String newScene, Controller c) throws IOException {
        FXMLLoader loader = new FXMLLoader(Yatzy.class.getResource(newScene));
        if (c != null) {
            loader.setController(c);
        }
        Parent root = loader.load();
        //loader.setController(this);
        Controller controller = loader.getController();
        //controller.setMainController(this);
        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        mainStage.setScene(scene);
        mainStage.show();
        return loader;
    }

    @Override
    public void setMainController(MainMenuController controller) {

    }
}
