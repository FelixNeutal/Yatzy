package com.example.controller;

import com.example.game.Game;
import com.example.game.HotSeatGame;
import com.example.game.SoloGame;
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
        if (game instanceof SoloGame) {
            soloPlayerName.setText("Player 1");
            soloPlayerScore.setText(String.valueOf(game.getPlayer1TotalScore()));
        } else if (game instanceof HotSeatGame) {
            player1Name.setText("Player 1");
            player1Score.setText(String.valueOf(game.getPlayer1TotalScore()));
            player2Name.setText("Player 2");
            player2Score.setText(String.valueOf(game.getPlayer2TotalScore()));
            if (game.getPlayer1TotalScore() > game.getPlayer2TotalScore()) {
                winnerLabel.setText("PLAYER 1 WON!");
            } else if (game.getPlayer1TotalScore() < game.getPlayer2TotalScore()) {
                winnerLabel.setText("PLAYER 2 WON!");
            } else {
                winnerLabel.setText("IT'S A TIE");
            }
        } else {
//            player1Name.setText(game.getPlayerName());
            player1Name.setText("You");
            player1Score.setText(String.valueOf(game.getPlayer1TotalScore()));
//            player2Name.setText(game.getOpponentName());
            player2Name.setText("Opponent");
            player2Score.setText(String.valueOf(game.getPlayer2TotalScore()));
            if (game.getPlayer1TotalScore() > game.getPlayer2TotalScore()) {
                winnerLabel.setText("YOU WON!");
            } else if (game.getPlayer1TotalScore() < game.getPlayer2TotalScore()) {
                winnerLabel.setText("YOU LOST!");
            } else {
                winnerLabel.setText("It's a tie");
            }
        }
    }
}
