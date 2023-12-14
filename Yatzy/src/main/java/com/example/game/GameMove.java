package com.example.game;

public class GameMove {
    private String dices;
    private int score;
    private ScoreType scoreType;
    private int scoreIndex;
    private int totalScore;
    private boolean gotBonus;
    private boolean gotYatzy;

    public GameMove() {

    }

    public GameMove(String dices, int score, ScoreType scoreType, int index) {
        this.dices = dices;
        this.score = score;
        this.scoreType = scoreType;
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

    public ScoreType getScoreType() {
        return scoreType;
    }

    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
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
}
