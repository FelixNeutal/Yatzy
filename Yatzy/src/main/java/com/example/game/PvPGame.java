package com.example.game;

import com.example.player.Player;

public class PvPGame extends Game {
    public PvPGame() {
        super();
    }

    @Override
    public GameMove onPlay(int index) {
        GameMove gameMove = new GameMove();
        player.addToScore(currentScores.get(index));
        //player.addToUpperSectionScore(score);
        if (index < UPPERSECTIONLIMIT) {
            gameMove.setGotBonus(player.addToBonusScore(currentScores.get(index)));
            gameMove.setUpperScore(player.getUpperSectionScore());
        }
        if (isYatzy()) { //Make it better
            if (index != YATZYCATEGORY) {
                System.out.println("Got new yatzy");
                gameMove.setGotYatzy(player.isAdditionalYatzy());
            } else {
                System.out.println("Got first yatzy");
                player.setIsYatzy();
            }
        }
        gameMove.setTotalScore(player.getTotalScore());
        currentRoundCount++;
        return  gameMove;
    }

    @Override
    public GameMove getOpponentMove() {
        return this.opponent.getPlayerMove();
    }
}
