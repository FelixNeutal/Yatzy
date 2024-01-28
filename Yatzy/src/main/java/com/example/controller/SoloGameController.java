package com.example.controller;

import com.example.game.GameMove;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class SoloGameController extends GameController {
    @Override
    @FXML
    protected void onPlayButtonClicked() {
        int buttonIndex = getScoreButtonIndex();
        GameMove gameMove = game.onPlay(buttonIndex);
        p1ScoreLabel.setText(String.valueOf(gameMove.getTotalScore()));
        p1BonusButton.setText(String.valueOf(gameMove.getUpperScore()));
        if (gameMove.isGotBonus()) {
            p1BonusButton.setStyle("-fx-background-color: orange;");
        }
        if (gameMove.isGotYatzy()) {
            p1ScoreButtons.get(buttonIndex).setStyle("-fx-background-color: orange;");
        }
        p1ScoreButtons.set(buttonIndex, new ToggleButton());
        disablePlayer1ScoreButtons();
        disableDiceButtons();
        disablePlayButton();
        clearDiceButtons();
        clearPlayer1ScoreButtons();
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

    @Override
    protected void setPlayer1Turn() {

    }

    @Override
    protected void setPlayer2Turn() {

    }
}
