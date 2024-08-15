package com.example.game;

import java.io.Serializable;

public class GameMove implements Serializable {
    private String dices = "";
    private int score = 0;
    private int scoreIndex = -1;
    private int totalScore = 0;
    private boolean gotBonus = false;
    private boolean gotYatzy = false;
    private int upperScore = 0;

    public GameMove() {

    }

    public GameMove(String dices, int score, int index) {
        this.dices = dices;
        this.score = score;
        this.scoreIndex = index;
    }

    public String getDices() {
        return dices;
    }

    public void setDices(String dices) {
        this.dices = dices;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setScoreIndex(int scoreIndex) {
        this.scoreIndex = scoreIndex;
    }

    public int getScoreIndex() {
        return scoreIndex;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public boolean isGotBonus() {
        return gotBonus;
    }

    public void setGotBonus(boolean gotBonus) {
        this.gotBonus = gotBonus;
    }

    public boolean isGotYatzy() {
        return gotYatzy;
    }

    public void setGotYatzy(boolean gotYatzy) {
        this.gotYatzy = gotYatzy;
    }

    public int getUpperScore() {
        return upperScore;
    }

    public void setUpperScore(int upperScore) {
        this.upperScore = upperScore;
    }

    @Override
    public String toString() {
        return "GameMove{" +
                "dices='" + dices + '\'' +
                ", score=" + score +
                ", scoreIndex=" + scoreIndex +
                ", totalScore=" + totalScore +
                ", gotBonus=" + gotBonus +
                ", gotYatzy=" + gotYatzy +
                ", upperScore=" + upperScore +
                '}';
    }
}
