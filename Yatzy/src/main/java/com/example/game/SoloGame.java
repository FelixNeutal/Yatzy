package com.example.game;

public class SoloGame extends Game {


    public SoloGame() {
        super();
        setGameType("soloGame");
    }

    public GameMove onPlay(int index) {
        GameMove gameMove = new GameMove();
        player.addToScore(currentScores.get(index));
        //player.addToUpperSectionScore(score);
        if (index < UPPERSECTIONLIMIT) {
            gameMove.setGotBonus(player.addToBonusScore(currentScores.get(index)));
        }
        gameMove.setUpperScore(player.getUpperSectionScore());
        if (isYatzy()) { //Make it better
            if (index != YATZYCATEGORY) {
                gameMove.setGotYatzy(player.isAdditionalYatzy());
            } else {
                player.setIsYatzy();
            }
        }
        gameMove.setTotalScore(player.getTotalScore());
        currentRoundCount++;
        return  gameMove;
    }

    @Override
    public GameMove getOpponentMove() {
        return null;
    }
}
