package com.example.game;

import com.example.player.RobotPlayer;

public class BotGame extends Game{
    public BotGame() {
        super();
        player2 = new RobotPlayer();
    }

    @Override
    public GameMove onPlay(int index) {
        player1.addToScore(currentScores.get(index));
        //player.addToUpperSectionScore(score);
        currentRoundCount++;
        switchCurrentPlayer();
        GameMove gameMove = new GameMove();
        return  gameMove;
    }

    @Override
    public GameMove getOpponentMove() {
        return this.player2.getPlayerMove();
    }

}
