package com.example.game;

public class SoloGame extends Game {


    public SoloGame() {
        super();
        setGameType("soloGame");
    }

    public void onPlay(int index) {
        player.addToScore(currentScores.get(index), index < UPPERSECTIONLIMIT);
        //player.addToUpperSectionScore(score);
        currentRoundCount++;
    }

    @Override
    public GameMove getOpponentMove() {
        return null;
    }
}
