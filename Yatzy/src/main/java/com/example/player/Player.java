package com.example.player;

import com.example.game.GameMove;
import com.example.gameLogic.Hand;

public class Player {
    private Hand hand;
    private int totalScore = 0;
    private int upperSectionScore = 0;
    private boolean isBonusSet = false;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void addToScore(int score) {
        this.totalScore += score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void addToUpperSectionScore(int score) {
        upperSectionScore += score;
        if (!isBonusSet && upperSectionScore >= 63) {
            isBonusSet = true;
            addToScore(35);
        }
    }

    public int getUpperSectionScore() {
        return upperSectionScore;
    }

    public GameMove getPlayerMove() {
        return new GameMove();
    }
}
