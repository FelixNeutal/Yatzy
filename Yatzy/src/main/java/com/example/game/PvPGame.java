package com.example.game;

import com.example.player.Player;

public class PvPGame extends Game {
    public PvPGame() {
        super();
    }

    @Override
    public GameMove onPlay(int index) {
        GameMove gameMove = new GameMove();
        gameMove.setScoreIndex(index);
        gameMove.setDices(getHand());
        gameMove.setScore(currentScores.get(index));
        player.addToScore(currentScores.get(index));
        //player.addToUpperSectionScore(score);
        if (index < UPPERSECTIONLIMIT) {
            gameMove.setGotBonus(player.addToBonusScore(currentScores.get(index)));
            gameMove.setUpperScore(player.getUpperSectionScore());
        }
        if (isYatzy()) { //Make it better
            if (index != YATZYCATEGORY) {
                gameMove.setGotYatzy(player.isAdditionalYatzy());
            } else {
                player.setIsYatzy();
            }
        }
        gameMove.setTotalScore(player.getTotalScore());
        currentRoundCount++;
        System.out.println(gameMove);
        return  gameMove;
    }

    @Override
    public GameMove getOpponentMove() {
        return this.opponent.getPlayerMove();
    }
}
