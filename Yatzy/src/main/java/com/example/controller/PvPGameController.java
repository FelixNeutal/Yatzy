package com.example.controller;

import com.example.game.GameMove;
import com.example.network.ConnectionListener;
import com.example.network.NetworkHandler;
import javafx.application.Platform;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

public class PvPGameController extends GameController {
    public void initializeConnection() {

    }
    @Override
    protected void onPlayButtonClicked() {
        ToggleButton button = getScoreButton();
        game.onPlay(p1ScoreButtons.indexOf(button));
        p1ScoreLabel.setText(String.valueOf(game.getPlayerScore()));
        p1ScoreButtons.set(p1ScoreButtons.indexOf(button), new ToggleButton());
        disableScoreButtons();
        disableDiceButtons();
        disablePlayButton();
        clearDiceButtons();
        clearScoreButtons();
        disableRollButton();
        unselectDice();
        currentTurnLabel.setText(opponentTurn);
    }

    public void OpponentMove(GameMove move) {
        new Thread(() -> {
            try {
                Platform.runLater(() -> {
                    printDice(move.getDices());
                });
                Thread.sleep(500);
                Platform.runLater(() -> {
                    p2ScoreButtons.get(move.getScoreIndex()).setText("" + move.getScore());
                    p2ScoreLabel.setText("" + move.getScore());
                    clearDiceButtons();
                });
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    if (!game.isRoundCountDone()) {
                        currentTurnLabel.setText(playerTurn);
                        enableRollButton();
                        game.resetCurrentRollCount();
                    } else {
                        //Show total winner
                        try {
                            mainController.endCurrentGame();
                        } catch (IOException e) {
                        }
                    }
                });
            } catch (InterruptedException ignore) {}
        }).start();
    }

    @Override
    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }
//    public BotGameController() {
//        System.out.println("Bot controller created");
//    }
//
//    @FXML
//    protected void onPlayButtonClicked() {
//        ToggleButton button = getScoreButton();
//        int score = Integer.parseInt(button.getText());
//        if (button.getId().equals("upperSection")) {
//            game.addUpperSectionScore(score);
//        }
//        game.onPlay(score);
//        button.setId("checked");
//        p1ScoreLabel.setText(String.valueOf(game.getPlayer1Score()));
//        disableScoreButtons();
//        disableDiceButtons();
//        disablePlayButton();
//        clearDiceButtons();
//
//        new Thread(() -> {
//            //Ai ai = new Ai();
//            //int value = ai.makeAMove();
//            Platform.runLater(() -> {
//                //welcomeText.setText("Value got was " + value);
//            });
//        }).start();
//
//        if (!game.isGameDone()) {
//            rollButton.setDisable(false);
//            game.resetCurrentRollCount();
//        } else {
//            //Show total winner
//            try {
//                mainController.endCurrentGame();
//            } catch (IOException e) {}
//        }
//    }
//
//    @Override
//    public void setGame(Game game) {
//        System.out.println("Setting game");
//        super.setGame(game);
//    }
}
