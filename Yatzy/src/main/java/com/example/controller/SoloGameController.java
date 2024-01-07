package com.example.controller;

import com.example.controller.GameController;
import com.example.game.GameMove;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class SoloGameController extends GameController {
    @Override
    @FXML
    protected void onPlayButtonClicked() {
        ToggleButton button = getScoreButton();
        GameMove gameMove = game.onPlay(p1ScoreButtons.indexOf(button));
        p1ScoreLabel.setText(String.valueOf(gameMove.getTotalScore()));
        System.out.println("Totalscore is " + gameMove.getTotalScore());
        p1BonusButton.setText(String.valueOf(gameMove.getUpperScore()));
        if (gameMove.isGotBonus()) {
            p1BonusButton.setStyle("-fx-background-color: orange;");
        }
        if (gameMove.isGotYatzy()) {
            p1ScoreButtons.get(p1ScoreButtons.indexOf(button)).setStyle("-fx-background-color: orange;");
        }
        p1ScoreButtons.set(p1ScoreButtons.indexOf(button), new ToggleButton());
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
