package com.example.player;

import com.example.game.GameMove;

import java.util.UUID;

public class Player implements IPlayer {
    private int totalScore = 0;
    private int upperSectionScore = 0;
    private boolean isBonus = false;
    private boolean isYatzy = false;
    private String name;
    private UUID uuid;
    private int playerNum;

    public Player(String name, int num) {
        this.name = name;
        this.playerNum = num;
    }

    public void addToScore(int score) {
        totalScore += score;
    }

    public boolean addToBonusScore(int score) {
        boolean bonusIsSet = false;
        if (upperSectionScore < 63) {
            upperSectionScore += score;
            bonusIsSet = upperSectionScore >= 63;
        }
        return bonusIsSet;
    }

    public boolean isAdditionalYatzy() {
        boolean yatzyIsSet = false;
        if (isYatzy) {
            totalScore += 50;
            yatzyIsSet = true;
        }
        return yatzyIsSet;
    }

    public int getUpperSectionScore() {
        return upperSectionScore;
    }

    public void setIsYatzy() {
        isYatzy = true;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getName() {
        return name;
    }

    public GameMove getPlayerMove() {
        return new GameMove();
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setUpperSectionScore(int score) {
        upperSectionScore += score;
    }
}
