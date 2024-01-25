//Maybe put mainmenucontroller here

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
    protected Player player;
    protected Player opponent;
    protected List<Integer> currentScores;
    protected String gameType = "";
    protected int opponentTotalScore;

    public Game() {
        player = new Player("Player 1");
        hand = new Hand();
        currentScores = new ArrayList<>();
    }

    //common method
    public int getPlayerScore() {
        return player.getTotalScore();
    }

//    public int getPlayerUpperScore() {
//        return player.getUpperSectionScore();
//    }
//
//    public boolean isBonusSet() {
//        return player.getIsBonusSet();
//    }

    public boolean isYatzy() {
        return hand.getYatzy() == 50;
    }

    //common method
    public int getOpponentScore() {
        return opponent.getTotalScore();
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

    //common method
    public boolean isWon() {
        return true;
    }

    public String getWinnerText() {
        String winText = "";
        if (opponent != null) {
            if (player.getTotalScore() > opponent.getTotalScore()) {
                winText = "You Won!";
            } else if (player.getTotalScore() < opponent.getTotalScore()) {
                winText = "You lost!";
            } else {
                winText = "It's a tie!";
            }
        }
        return winText;
    }

    //common method
    public void switchCurrentPlayer() {
//        if (currentPlayer.equals(player)) {
//            currentPlayer = player2;
//        } else {
//            currentPlayer = player;
//        }
    }

    public abstract GameMove getOpponentMove();

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String type) {
        gameType = type;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public String getOpponentName() {
        return opponent.getName();
    }

    public int getOpponentTotalScore() {
        return opponentTotalScore;
    }

    public void setOpponentTotalScore(int opponentTotalScore) {
        this.opponentTotalScore = opponentTotalScore;
    }
}
