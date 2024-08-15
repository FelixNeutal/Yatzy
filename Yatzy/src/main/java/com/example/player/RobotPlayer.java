//https://www.csc.kth.se/utbildning/kth/kurser/DD143X/dkand12/Group89Michael/report/Larsson+Sjoberg.pdf

package com.example.player;

import com.example.game.GameMove;

import java.util.ArrayList;
import java.util.List;

public class RobotPlayer extends Player {
    private int totalScore = 0;
    private List<GameMove> moves = new ArrayList<>();
    private int randomIndex = 12;

    public RobotPlayer() {
        super("robot", 2);
        moves.add(new GameMove("12215", 2, 0));
        moves.add(new GameMove("32212", 6, 1));
        moves.add(new GameMove("32133", 9, 2));
        moves.add(new GameMove("44144", 16, 3));
        moves.add(new GameMove("54122", 5, 4));
        moves.add(new GameMove("56662", 18, 5));

        moves.add(new GameMove("26662", 22, 6));
        moves.add(new GameMove("34444", 19, 7));
        moves.add(new GameMove("34433", 25, 8));
        moves.add(new GameMove("34152", 30, 9));
        moves.add(new GameMove("24356", 40, 10));
        moves.add(new GameMove("11111", 50, 11));
        moves.add(new GameMove("14421", 12, 12));
    }

    @Override
    public GameMove getPlayerMove() {
        //GameMove(String dices, int score, ScoreType scoreType)
        try {
            Thread.sleep((long) (Math.random() * 2000) + 1000);
        } catch (InterruptedException ignored) {}
        return getRandomScore();
    }

    private GameMove getRandomScore() {
        int index = (int)(Math.random() * randomIndex);
        GameMove move = moves.get(index);
        moves.remove(index);
        totalScore += move.getScore();
        move.setTotalScore(totalScore);
        randomIndex--;
        return move;
    }
}
