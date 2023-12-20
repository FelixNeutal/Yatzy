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
        int score = Integer.parseInt(button.getText());
        if (button.getId().equals("upperSection")) {
            game.addUpperSectionScore(score);
        }
        game.onPlay(p1ScoreButtons.indexOf(button), score);
        button.setId("checked");
        p1ScoreLabel.setText(String.valueOf(game.getPlayerScore()));
        disableScoreButtons();
        disableDiceButtons();
        disablePlayButton();
        clearDiceButtons();
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
