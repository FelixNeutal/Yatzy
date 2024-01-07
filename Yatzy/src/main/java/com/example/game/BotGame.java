package com.example.game;

import com.example.player.Player;
import com.example.player.RobotPlayer;

public class BotGame extends Game{
    public BotGame() {
        super();
        opponent = new RobotPlayer();
    }

    @Override
    public GameMove onPlay(int index) {
        player.addToScore(currentScores.get(index));
        //player.addToUpperSectionScore(score);
        currentRoundCount++;
        switchCurrentPlayer();
        GameMove gameMove = new GameMove();
        return  gameMove;
    }

    @Override
    public GameMove getOpponentMove() {
        return this.opponent.getPlayerMove();
    }

}
