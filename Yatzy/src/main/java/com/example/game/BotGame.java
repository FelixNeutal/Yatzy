package com.example.game;

import com.example.player.Player;
import com.example.player.RobotPlayer;

public class BotGame extends Game{
    public BotGame() {
        super();
        opponent = new RobotPlayer();
    }

    @Override
    public void onPlay(int index) {
        player.addToScore(currentScores.get(index), index < UPPERSECTIONLIMIT);
        //player.addToUpperSectionScore(score);
        currentRoundCount++;
        switchCurrentPlayer();
    }

    @Override
    public GameMove getOpponentMove() {
        return opponent.getPlayerMove();
    }
}
