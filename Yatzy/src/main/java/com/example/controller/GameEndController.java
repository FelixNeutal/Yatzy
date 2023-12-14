package com.example.controller;

import com.example.game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameEndController implements Controller {
    @FXML
    private Button newGameButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;
    @FXML
    private Label winnerLabel;
    private MainMenuController mainController;
    private Game game;

    @FXML
    protected void onNewGameButton() throws IOException {
        mainController.onSoloGameClick();
    }

    @FXML
    protected void onExitButton() throws IOException {
        mainController.backToMainMenu();
    }

    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setSoloGameEndInfo() {
        player1Score.setText(String.valueOf(game.getPlayerScore()));
        winnerLabel.setText("Congrats!!!");
    }

    public void setGameEndInfo() {

    }
}
