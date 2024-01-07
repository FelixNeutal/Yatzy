package com.example.controller;

import com.example.game.Game;
import com.example.game.GameMove;
import com.example.yatzy.PersistentButtonToggleGroup;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GameController implements Controller {
    @FXML
    protected Button rollButton;
    @FXML
    protected Button playButton;
    @FXML
    protected GridPane mainGrid;
    @FXML
    protected GridPane diceGrid;
    @FXML
    protected Label p1NameLabel;
    @FXML
    protected Label p2NameLabel;
    @FXML
    protected Label p1NameLabelB;
    @FXML
    protected Label p2NameLabelB;
    @FXML
    protected Label p1ScoreLabel;
    @FXML
    protected Label p2ScoreLabel;
    @FXML
    protected Label p1ScoreNameLabel;
    @FXML
    protected Label p2ScoreNameLabel;
    @FXML
    protected Label currentTurnLabel;
    @FXML
    protected Label yatzyLabel;

    protected String playerTurn;
    protected String opponentTurn;

    protected ToggleButton p1BonusButton;
    protected ToggleButton p2BonusButton;
    protected List<ToggleButton> p1ScoreButtons = new ArrayList<>();
    protected List<ToggleButton> p2ScoreButtons = new ArrayList<>();
    protected List<ToggleButton> diceButtons = new ArrayList<>();
    protected PersistentButtonToggleGroup p1Group = new PersistentButtonToggleGroup();
    public Game game;
    protected MainMenuController mainController;
    @FXML
    protected Button testButton;
    @FXML
    protected ToggleButton testToggleButton;

    @FXML
    public void initialize() {
        createScoreButtons();
        createDiceButtons();
        p1NameLabel.setText("Player1");
        p1NameLabelB.setText("Player1");
        p2NameLabel.setText("Player2");
        p2NameLabelB.setText("Player2");
        playerTurn   = "  Your turn   ";
        opponentTurn = "Opponents turn";
        playButton.setDisable(true);
        currentTurnLabel.setText(playerTurn);
    }

    @FXML
    protected void onRollButtonClicked() {
        game.onRoll(getUnSelectedDice());
        enableDiceButtons();
        //enableScoreButtons();
        printDice(game.getHand());
        printScores(game.getScores());
        //unselectDice();
        unselectScore();
        rollButton.setDisable(game.isRollCountDone());
        if (game.isYatzy()) {
            yatzyLabel.setText("YATZY!!!");
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    Platform.runLater(() -> {
                        yatzyLabel.setText("");
                    });
                } catch (InterruptedException ignore) {}
            }).start();
        }
    }

    protected void setYatzyText() {
        yatzyLabel.setText("YATZY!!!");
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                Platform.runLater(() -> {
                    yatzyLabel.setText("");
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).run();
    }

    @FXML
    protected abstract void onPlayButtonClicked();

    @FXML
    protected void onScoreButtonToggled(ActionEvent actionEvent) {
        playButton.setDisable(false);
    }

    @FXML
    protected void onDiceButtonToggled() {
        playButton.setDisable(false);
    }

    protected ToggleButton getScoreButton() {
        ToggleButton button = null;
        for (ToggleButton b: p1ScoreButtons) {
            if (b.isSelected()) {
                button = b;
            }
        }
        button.setSelected(false);
        button.setDisable(true);
        //p1ScoreButtons.remove(button);
        return button;
    }

    protected boolean isUpperSection() {
        for (ToggleButton b: p1ScoreButtons) {
            if (b.isSelected() && b.getId().equals("upperSection")) {
                return true;
            }
        }
        return false;
    }

    protected void clearDiceButtons() {
        for (ToggleButton b : diceButtons) {
            b.setText(" ");
        }
    }

    protected void clearScoreButtons() {
        for (ToggleButton b : p1ScoreButtons) {
            b.setText(" ");
        }
    }

    protected void enablePlayButton() {
        playButton.setDisable(false);
    }

    protected void disablePlayButton() {
        playButton.setDisable(true);
    }

    protected void enableScoreButtons() {
        for (ToggleButton b : p1ScoreButtons) {
            b.setDisable(false);
        }
    }

    protected void disableScoreButtons() {
        for (ToggleButton b : p1ScoreButtons) {
            b.setDisable(true);
            b.setSelected(false);
        }
    }

    protected void enableDiceButtons() {
        for (ToggleButton b : diceButtons) {
            b.setDisable(false);
        }
    }

    protected void disableDiceButtons() {
        for (ToggleButton b : diceButtons) {
            b.setDisable(true);
            b.setText(" ");
        }
    }

    protected void disableRollButton() {
        rollButton.setDisable(true);
    }

    protected void enableRollButton() {
        rollButton.setDisable(false);
    }

    protected void unselectScore() {
        for (ToggleButton b : p1ScoreButtons) {
            b.setSelected(false);
        }
    }

    protected void createDiceButtons() {
        ToggleButton b;
        for (int i = 2; i < 7; i++) {
            b = new ToggleButton(" ");
            b.setPrefSize(32.0, 32.0);
            b.setDisable(true);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    ToggleButton tb = (ToggleButton) actionEvent.getSource();
                    if (tb.isSelected()) {
                        tb.setStyle("-fx-border-color: orange; -fx-border-width: 0.5em;");
                    } else {
                        tb.setStyle("-fx-border-color: none;");
                    }
                }
            });
            diceButtons.add(b);
            diceGrid.add(b, i, 0);
        }
    }

    protected void createScoreButtons() {
        ToggleButton b, c;
        for (int i = 0; i < 6; i++) {
            b = new ToggleButton("St");
            b.setPrefSize(32.0, 32.0);
            //b.getStyleClass().setAll("border-color: #04AA6D;");
            b.setToggleGroup(p1Group);
            b.setDisable(true);
            b.setId("upperSection");
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    onScoreButtonToggled(actionEvent);
                }
            });
            mainGrid.add(b, 1, i);
            p1ScoreButtons.add(b);
            b.setToggleGroup(p1Group);
        }
        p1BonusButton = new ToggleButton("  ");
        p1BonusButton.setPrefSize(32.0, 32.0);
        p1BonusButton.setDisable(true);
        mainGrid.add(p1BonusButton, 1, 6);

        p2BonusButton = new ToggleButton("  ");
        p2BonusButton.setPrefSize(32.0, 32.0);
        p2BonusButton.setDisable(true);
        mainGrid.add(p2BonusButton, 2, 6);

        for (int i = 0; i < 7; i++) {
            b = new ToggleButton("  ");
            b.setPrefSize(32.0, 32.0);
            b.setToggleGroup(p1Group);
            b.setDisable(true);
            b.setId("null");
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    onScoreButtonToggled(actionEvent);
                }
            });
            mainGrid.add(b, 5, i);
            p1ScoreButtons.add(b);
            b.setToggleGroup(p1Group);
        }

        for (int i = 0; i < 6; i++) {
            c = new ToggleButton("  ");
            c.setPrefSize(32.0, 32.0);
            c.setDisable(true);
            c.setId("null");
            mainGrid.add(c, 2, i);
            p2ScoreButtons.add(c);
        }

        for (int i = 0; i < 7; i++) {
            c = new ToggleButton("  ");
            c.setPrefSize(32.0, 32.0);
            c.setDisable(true);
            c.setId("null");
            mainGrid.add(c, 6, i);
            p2ScoreButtons.add(c);
        }
    }

    protected void hideSecondPlayerScores() {
        for (ToggleButton b: p2ScoreButtons) {
            b.setVisible(true);
        }
    }

    protected List<Integer> getUnSelectedDice() {
        List<Integer> selected = new ArrayList<>();
        for (ToggleButton b : diceButtons) {
            if (!b.isSelected()) {
                selected.add(diceButtons.indexOf(b));
            }
        }
        return selected;
    }

    protected Integer getSelectedScore() {
        Integer score = 0;
        for (ToggleButton b : p1ScoreButtons) {
            if (b.isSelected()) {
                score = Integer.parseInt(b.getText());
            }
        }
        return score;
    }

    protected void unselectDice() {
        for (ToggleButton b : diceButtons) {
            b.setSelected(false);
            b.setStyle("-fx-border-color: none;");
        }
    }

    protected void printScores(List<Integer> scores) {
        for (int i = 0; i < p1ScoreButtons.size(); i++) {
                p1ScoreButtons.get(i).setText(scores.get(i).toString());
                p1ScoreButtons.get(i).setDisable(false);
        }
    }

    protected void printDice(String hand) {
        for (int i = 0; i < diceButtons.size(); i++) {
            diceButtons.get(i).setText(String.valueOf(hand.charAt(i)));
        }
    }

    public void setMainController(MainMenuController controller) {
        mainController = controller;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
