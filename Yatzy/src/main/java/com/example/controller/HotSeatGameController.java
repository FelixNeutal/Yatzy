package com.example.controller;

import com.example.game.GameMove;
import javafx.scene.control.ToggleButton;
import java.io.IOException;

public class HotSeatGameController extends GameController {
    public HotSeatGameController() {
        player1Turn = "Player 1 turn";
        player2Turn = "Player 2 turn";
    }

//    @Override
//    protected void onPlayButtonClicked() {
//        int buttonIndex = getScoreButtonIndex();
//        GameMove gameMove = game.onPlay(buttonIndex);
//        if (game.getCurrentPlayer().getPlayerNum() == 1) {
//            setPlayer1ScoreInfo(gameMove, buttonIndex);
//        } else {
//            setPlayer2ScoreInfo(gameMove, buttonIndex);
//        }
//        if (!game.isRoundCountDone()) {
//            game.switchCurrentPlayer();
//            if (game.getCurrentPlayer().getPlayerNum() == 1) {
//                setPlayer1Turn();
//            } else {
//                setPlayer2Turn();
//            }
//        } else {
//            try {
//                mainController.endCurrentGame();
//            } catch (IOException e) {}
//        }
//    }

    protected void setPlayer1Turn() {
        currentTurnLabel.setText(player1Turn);
        enableRollButton();
        clearDiceButtons();
        game.resetCurrentRollCount();
        disablePlayer2ScoreButtons();
        clearPlayer2ScoreButtons();
        unselectPlayer2ScoreButtons();
    }

    protected void setPlayer2Turn() {
        currentTurnLabel.setText(player2Turn);
        enableRollButton();
        clearDiceButtons();
        game.resetCurrentRollCount();
        disablePlayer1ScoreButtons();
        clearPlayer1ScoreButtons();
        unselectPlayer1ScoreButtons();
    }

    protected void setPlayer1ScoreInfo(GameMove gameMove, int buttonIndex) {
        p1ScoreLabel.setText(String.valueOf(gameMove.getTotalScore()));
        p1BonusButton.setText(String.valueOf(gameMove.getUpperScore()));
        if (gameMove.isGotBonus()) {
            p1BonusButton.setStyle("-fx-background-color: orange;");
        }
        if (gameMove.isGotYatzy()) {
            p1ScoreButtons.get(buttonIndex).setStyle("-fx-background-color: orange;");
        }
        p1ScoreButtons.set(buttonIndex, new ToggleButton());
    }

    protected void setPlayer2ScoreInfo(GameMove gameMove, int buttonIndex) {
        p2ScoreLabel.setText(String.valueOf(gameMove.getTotalScore()));
        p2BonusButton.setText(String.valueOf(gameMove.getUpperScore()));
        if (gameMove.isGotBonus()) {
            p2BonusButton.setStyle("-fx-background-color: orange;");
        }
        if (gameMove.isGotYatzy()) {
            p2ScoreButtons.get(buttonIndex).setStyle("-fx-background-color: orange;");
        }
        p2ScoreButtons.set(buttonIndex, new ToggleButton());
    }

}
