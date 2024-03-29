package com.example.game;

import com.example.gameLogic.Hand;
import com.example.player.Player;


import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    //13 rounds total
    protected final int UPPERSECTIONLIMIT = 6;
    protected final int YATZYCATEGORY = 11;
    protected int totalRoundCount = 13;
    protected int currentRoundCount = 0;
    protected int diceRollCountLimit = 3;
    protected int currentRollCount = 0;
    protected Hand hand;
    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;
    protected List<Integer> currentScores;
    protected int player2TotalScore;

    public Game() {
        player1 = new Player("Player 1", 1);
        hand = new Hand();
        currentScores = new ArrayList<>();
    }

    //common method
    public int getPlayerScore() {
        return player1.getTotalScore();
    }

    public boolean isYatzy() {
        return hand.getYatzy() == 50;
    }

    //common method
    public String onRoll(List<Integer> selected) {
        currentRollCount++;
        return hand.generateDiceNumbers(selected);
    }

    //override this method
    public abstract GameMove onPlay(int index);

    //common method
    public String getHand() {
        return hand.getHand();
    }

    //common method
    public List<Integer> getScores() {
        currentScores = hand.getAllScores();
        return currentScores;
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

    public abstract GameMove getOpponentMove();

    public String getPlayerName() {
        return player1.getName();
    }

    public int getPlayer1TotalScore() {
        return player1.getTotalScore();
    }

    public int getPlayer2TotalScore() {
        return player2.getTotalScore();
    }

    public void setPlayer2TotalScore(int opponentTotalScore) {
        this.player2TotalScore = opponentTotalScore;
    }
}
