package com.example.game;

public class SoloGame extends Game {


    public SoloGame() {
        super();

    }

    public void onPlay(int score) {
        player.addToScore(score);
        //player.addToUpperSectionScore(score);
        currentRoundCount++;
    }

    @Override
    public GameMove getOpponentMove() {
        return null;
    }
}
