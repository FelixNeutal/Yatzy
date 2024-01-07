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
    private Label soloPlayerScore;
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;
    @FXML
    private Label winnerLabel;
    @FXML
    private Label soloPlayerName;
    @FXML
    private Label player1Name;
    @FXML
    private Label player2Name;
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

    public void setGameEndInfo() {
        winnerLabel.setText(game.getWinnerText());
        if (game.getGameType().equals("soloGame")) {
            soloPlayerName.setText(game.getPlayerName());
            soloPlayerScore.setText(String.valueOf(game.getPlayerScore()));
        } else {
            player1Name.setText(game.getPlayerName());
            player1Score.setText(String.valueOf(game.getPlayerScore()));
            player2Name.setText(game.getOpponentName());
            player2Score.setText(String.valueOf(game.getOpponentScore()));
        }
    }
}
