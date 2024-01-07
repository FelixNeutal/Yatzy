package com.example.player;

import com.example.game.GameMove;
import com.example.gameLogic.Hand;

public class Player {
    private Hand hand;
    private int totalScore = 0;
    private int upperSectionScore = 0;
    private boolean isBonusSet = false;
    private boolean isYatzyCategory = false;
    private boolean isYatzySet = false;
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

    public void addToScore(int score, boolean isUpperSection) {
        totalScore += score;
        if (isUpperSection && upperSectionScore < 63) {
            upperSectionScore += score;
            if (upperSectionScore >= 63) {
                upperSectionScore = 63;
                totalScore += 35;
            }
        }
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getUpperSectionScore() {
        return upperSectionScore;
    }

    public GameMove getPlayerMove() {
        return new GameMove();
    }

    public boolean getIsBonusSet() {
        isBonusSet = upperSectionScore >= 63;
        return isBonusSet;
    }
}
