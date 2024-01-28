package com.example.game;

public class SoloGame extends Game {


    public SoloGame() {
        super();
    }

    public GameMove onPlay(int index) {
        GameMove gameMove = new GameMove();
        player1.addToScore(currentScores.get(index));
        if (index < UPPERSECTIONLIMIT) {
            gameMove.setGotBonus(player1.addToBonusScore(currentScores.get(index)));
        }
        gameMove.setUpperScore(player1.getUpperSectionScore());
        if (isYatzy()) { //Make it better
            if (index != YATZYCATEGORY) {
                gameMove.setGotYatzy(player1.isAdditionalYatzy());
            } else {
                player1.setIsYatzy();
            }
        }
        gameMove.setTotalScore(player1.getTotalScore());
        currentRoundCount++;
        return  gameMove;
    }

    @Override
    public GameMove getOpponentMove() {
        return null;
    }
}
