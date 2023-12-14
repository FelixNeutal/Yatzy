//Maybe put mainmenucontroller here

package com.example.game;

import com.example.gameLogic.Hand;
import com.example.player.Player;


import java.util.List;

public abstract class Game {
    //13 rounds total
    protected int totalRoundCount = 13;
    protected int currentRoundCount = 0;
    protected int diceRollCountLimit = 3;
    protected int currentRollCount = 0;
    protected Hand hand;
    protected Player player;
    protected Player opponent;

    public Game() {
        player = new Player("Player 1");
        hand = new Hand();
    }

    //common method
    public int getPlayerScore() {
        return player.getTotalScore();
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
    public void onPlay(int index, int score) {
        player.addToScore(score);
        //player.addToUpperSectionScore(score);
        currentRoundCount++;
    }

    //common method
    public void addUpperSectionScore(int score) {
        player.addToUpperSectionScore(score);
    }

    //common method
    public String getHand() {
        return hand.getHand();
    }

    //common method
    public List<Integer> getScores() {
        return hand.getAllScores();
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
    public void saveResults() {}

    //common method
    public boolean isWon() {
        return true;
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
}
