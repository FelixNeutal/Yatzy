package com.example.controller;

import com.example.game.GameMove;
import javafx.application.Platform;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

public class BotGameController extends GameController {
//    @Override
//    protected void onPlayButtonClicked() {
//        int buttonIndex = getScoreButtonIndex();
//        game.onPlay(buttonIndex);
//        p1ScoreLabel.setText(String.valueOf(game.getCurrentPlayerScore()));
//        p1ScoreButtons.set(buttonIndex, new ToggleButton());
//        disablePlayer1ScoreButtons();
//        disableDiceButtons();
//        disablePlayButton();
//        clearDiceButtons();
//        clearPlayer1ScoreButtons();
//        disableRollButton();
//        unselectDice();
//        currentTurnLabel.setText(player2Turn);
//        new Thread(() -> {
//            try {
//                GameMove move = game.getOpponentMove();
//                Platform.runLater(() -> {
//                    printDice(move.getDices());
//                });
//                Thread.sleep(500);
//                Platform.runLater(() -> {
//                    p2ScoreButtons.get(move.getScoreIndex()).setText("" + move.getScore());
//                    p2ScoreLabel.setText("" + move.getScore());
//                    clearDiceButtons();
//                });
//                Thread.sleep(1000);
//                Platform.runLater(() -> {
//                    if (!game.isRoundCountDone()) {
//                        currentTurnLabel.setText(player1Turn);
//                        enableRollButton();
//                        game.resetCurrentRollCount();
//                    } else {
//                        //Show total winner
//                        try {
//                            mainController.endCurrentGame();
//                        } catch (IOException e) {
//                        }
//                    }
//                });
//            } catch (InterruptedException ignore) {}
//        }).start();
//    }
}
