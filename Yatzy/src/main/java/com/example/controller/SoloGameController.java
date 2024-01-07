package com.example.controller;

import com.example.controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class SoloGameController extends GameController {
    @Override
    @FXML
    protected void onPlayButtonClicked() {
        ToggleButton button = getScoreButton();
        game.onPlay(p1ScoreButtons.indexOf(button));
        p1ScoreLabel.setText(String.valueOf(game.getPlayerScore()));
        p1ScoreButtons.set(p1ScoreButtons.indexOf(button), new ToggleButton());
        p1BonusButton.setText(String.valueOf(game.getPlayerUpperScore()));
        if (game.isBonusSet()) {
            p1BonusButton.setStyle("-fx-background-color: orange;");
        }
        disableScoreButtons();
        disableDiceButtons();
        disablePlayButton();
        clearDiceButtons();
        clearScoreButtons();
        unselectDice();
        if (!game.isRoundCountDone()) {
            rollButton.setDisable(false);
            game.resetCurrentRollCount();
        } else {
            //Show total winner
            try {
                mainController.endCurrentGame();
            } catch (IOException e) {}
        }
    }
}
