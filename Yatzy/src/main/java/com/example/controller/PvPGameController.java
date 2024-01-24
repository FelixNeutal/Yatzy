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
        session.send(new Packet(Header.OPPONENT_MOVE, gameMove));
        setOpponentssTurn();
    }

    protected void setPlayersTurn() {
        currentTurnLabel.setText(playerTurn);
        enableRollButton();
        clearDiceButtons();
        game.resetCurrentRollCount();
    }

    protected void setOpponentssTurn() {
        currentTurnLabel.setText(opponentTurn);
        disableScoreButtons();
        disableDiceButtons();
        disablePlayButton();
        clearDiceButtons();
        clearScoreButtons();
        disableRollButton();
        unselectDice();
    }

    public void OpponentMove(GameMove move) {
        opponentTurns++;
        new Thread(() -> {
            try {
                Platform.runLater(() -> {
                    printDice(move.getDices());
                });
                Thread.sleep(500);
                Platform.runLater(() -> {
                    p2ScoreButtons.get(move.getScoreIndex()).setText("" + move.getScore());
                    p2ScoreLabel.setText("" + move.getTotalScore());
                    clearDiceButtons();
                });
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    if (!game.isRoundCountDone()) {
                        setPlayersTurn();
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
