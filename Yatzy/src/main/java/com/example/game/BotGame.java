package com.example.game;

import com.example.player.Player;
import com.example.player.RobotPlayer;

public class BotGame extends Game{
    public BotGame() {
        super();
        opponent = new RobotPlayer();
        System.out.println("bot game created");
    }

    @Override
    public void onPlay(int index, int score) {
        player.addToScore(score);
        //player.addToUpperSectionScore(score);
        currentRoundCount++;
        switchCurrentPlayer();
    }

    @Override
    public GameMove getOpponentMove() {
        return opponent.getPlayerMove();
    }
}
