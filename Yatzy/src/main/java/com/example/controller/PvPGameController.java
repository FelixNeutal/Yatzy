package com.example.controller;

import com.example.game.GameMove;
import com.example.network.*;
import javafx.application.Platform;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

public class PvPGameController extends GameController {
    private Session session;
    private int playerTurns = 0;
    private int opponentTurns = 0;
    private int totalTurns = 13;
    @Override
    protected void onPlayButtonClicked() {
        playerTurns++;
        int buttonIndex = getScoreButtonIndex();
        GameMove gameMove = game.onPlay(buttonIndex);
        p1ScoreLabel.setText(String.valueOf(gameMove.getTotalScore()));
        System.out.println("Totalscore is " + gameMove.getTotalScore());
        p1BonusButton.setText(String.valueOf(gameMove.getUpperScore()));
        if (gameMove.isGotBonus()) {
            p1BonusButton.setStyle("-fx-background-color: orange;");
        }
        if (gameMove.isGotYatzy()) {
            p1ScoreButtons.get(buttonIndex).setStyle("-fx-background-color: orange;");
        }
        p1ScoreButtons.set(buttonIndex, new ToggleButton());
        session.send(new Packet(Header.OPPONENT_MOVE, gameMove));
        shouldEndGame();
        setPlayer2Turn();
    }

    protected void setPlayer1Turn() {
        currentTurnLabel.setText(player1Turn);
        enableRollButton();
        clearDiceButtons();
        game.resetCurrentRollCount();
    }

    protected void setPlayer2Turn() {
        currentTurnLabel.setText(player2Turn);
        disablePlayer1ScoreButtons();
        disableDiceButtons();
        disablePlayButton();
        clearDiceButtons();
        clearPlayer1ScoreButtons();
        disableRollButton();
        unselectDice();
    }

    public void OpponentMove(GameMove move) {
        opponentTurns++;
        game.setOpponentTotalScore(move.getTotalScore());
        new Thread(() -> {
            try {
                Platform.runLater(() -> {
                    printDice(move.getDices());
                });
                Thread.sleep(800);
                Platform.runLater(() -> {
                    p2ScoreButtons.get(move.getScoreIndex()).setText("" + move.getScore());
                    p2ScoreLabel.setText("" + move.getTotalScore());
                    clearDiceButtons();
                });
                Thread.sleep(700);
                Platform.runLater(() -> {
                    if (!game.isRoundCountDone()) {
                        setPlayer1Turn();
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
        shouldEndGame();
    }

    private void shouldEndGame() {
        if (playerTurns == totalTurns && opponentTurns == totalTurns) {
            try {
                mainController.endCurrentGame();
            } catch (IOException e) {}
        }
    }

    @Override
    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
