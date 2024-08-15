package com.example.game;

import com.example.player.Player;

public class HotSeatGame extends Game{
    public HotSeatGame() {
        super();
        totalRoundCount *= 2;
    }
//
//    @Override
//    public GameMove onPlay(int index) {
//        GameMove gameMove = new GameMove();
//        gameMove.setScoreIndex(index);
//        gameMove.setDices(getHand());
//        gameMove.setScore(currentScores.get(index));
//        currentPlayer.addToScore(currentScores.get(index));
//        //player.addToUpperSectionScore(score);
//        if (index < UPPERSECTIONLIMIT) {
//            gameMove.setGotBonus(currentPlayer.addToBonusScore(currentScores.get(index)));
//            gameMove.setUpperScore(currentPlayer.getUpperSectionScore());
//        }
//        if (isYatzy()) { //Make it better
//            if (index != YATZYCATEGORY) {
//                gameMove.setGotYatzy(currentPlayer.isAdditionalYatzy());
//            } else {
//                currentPlayer.setIsYatzy();
//            }
//        }
//        gameMove.setTotalScore(currentPlayer.getTotalScore());
//        currentRoundCount++;
//        return  gameMove;
//    }
//
//    @Override
//    public GameMove getOpponentMove() {
//        return this.player2.getPlayerMove();
//    }
}
