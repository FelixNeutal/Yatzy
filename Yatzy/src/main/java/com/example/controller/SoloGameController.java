package com.example.controller;

import com.example.controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class SoloGameController extends GameController {
    @Override
    @FXML
    protected void onRollButtonClicked() {
        game.onRoll(getUnSelectedDice());
        enableDiceButtons();
        //enableScoreButtons();
        printDice(game.getHand());
        printScores(game.getScores());
        unselectDice();
        unselectScore();
        rollButton.setDisable(game.isRollCountDone());
    }

    @Override
    @FXML
    protected void onPlayButtonClicked() {
        ToggleButton button = getScoreButton();
        int score = Integer.parseInt(button.getText());
        if (button.getId().equals("upperSection")) {
            game.addUpperSectionScore(score);
        }
        game.onPlay(-1, score);
        button.setId("checked");
        p1ScoreLabel.setText(String.valueOf(game.getPlayerScore()));
        disableScoreButtons();
        disableDiceButtons();
        disablePlayButton();
        clearDiceButtons();
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
