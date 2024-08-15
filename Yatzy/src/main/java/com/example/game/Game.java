package com.example.game;

import com.example.gameLogic.HandLogic;
import com.example.player.Player;


import java.util.List;

public abstract class Game {
    //13 rounds total
    protected final int UPPERSECTIONLIMIT = 6;
    protected final int YATZYCATEGORY = 11;
    protected int totalRoundCount = 13;
    protected int currentRoundCount = 0;
    protected int diceRollCountLimit = 3;
    protected int currentRollCount = 0;
    protected String hand;
    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;

    public Game() {
        player1 = new Player("Player 1", 1);
        player2 = new Player("Player 2", 2);
        hand = "";
        currentPlayer = player1;
    }

    //common method
    public int getCurrentPlayerScore() {
        return currentPlayer.getTotalScore();
    }

    public boolean isYatzy() {
        return HandLogic.getYatzy(hand) == 50;
    }

    //common method
    public String onRoll(List<Integer> selected) {
        currentRollCount++;
        hand = HandLogic.generateDiceNumbers(selected, hand);
        return hand;
    }

    public void onPlay(int index) {
        int score = HandLogic.getAllScores(hand).get(index);
        currentPlayer.addToScore(score);
        if (index < UPPERSECTIONLIMIT) {
            currentPlayer.setUpperSectionScore(score);
        }
        resetCurrentRollCount();
        currentRoundCount++;
    }

    public List<Integer> getAllScores() {
        return HandLogic.getAllScores(hand);
    }

    //common method
    public boolean isRollCountDone() {
        return currentRollCount >= diceRollCountLimit;
    }

    public boolean isRoundCountDone() {
        return currentRoundCount >= totalRoundCount;
    }

    //common method
    public void resetCurrentRollCount() {
        currentRollCount = 0;
    }

    //common method
    public void saveResults() {
    }

    public String getWinnerText() {
        String winText = "";
        if (player2 != null) {
            if (player1.getTotalScore() > player2.getTotalScore()) {
                winText = "You Won!";
            } else if (player1.getTotalScore() < player2.getTotalScore()) {
                winText = "You lost!";
            } else {
                winText = "It's a tie!";
            }
        }
        return winText;
    }

    //common method
    public void switchCurrentPlayer() {
        if (currentPlayer.equals(player1)) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getPlayer1TotalScore() {
        return player1.getTotalScore();
    }

    public int getPlayer2TotalScore() {
        return player2.getTotalScore();
    }
}
