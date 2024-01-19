package com.example.controller;

import com.example.network.NetworkHandler;
import com.example.network.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NetworkMenuController implements Controller {
    @FXML
    Button hostGameButton;
    @FXML
    Label hostGameLabel;
    @FXML
    TextField hostGameInput;
    @FXML
    Button joinGameButton;
    @FXML
    Label joinGameLabel;
    @FXML
    TextField joinGameInput;
    @FXML
    Button mainMenuButton;
    private MainMenuController mainMenuController;
    Session session;

    @Override
    public void setMainController(MainMenuController controller) {
        this.mainMenuController = controller;
    }

    @FXML
    public void onHostGameClick() {
        hostGameLabel.setText("Hosting a game");
        NetworkHandler networkHandler = new NetworkHandler();
        session = new Session();
        session.createServer("2020");
    }

    @FXML
    public void onJoinGameClick() {
        joinGameLabel.setText("Joining a game");
    }

    @FXML
    public void onMainMenuClick() throws IOException {
        mainMenuController.backToMainMenu();
    }

    public void startGame() throws IOException {
        mainMenuController.backToMainMenu();
        mainMenuController.startPvPGame(session);
    }
}
