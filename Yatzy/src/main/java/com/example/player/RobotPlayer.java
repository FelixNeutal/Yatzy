//https://www.csc.kth.se/utbildning/kth/kurser/DD143X/dkand12/Group89Michael/report/Larsson+Sjoberg.pdf

package com.example.player;

import com.example.game.GameMove;
import com.example.game.ScoreType;

import java.util.ArrayList;
import java.util.List;

public class RobotPlayer extends Player {
    private int totalScore = 0;
    private List<GameMove> moves = new ArrayList<>();

    public RobotPlayer() {
        super("robot");
        moves.add(new GameMove("12215", 2, ScoreType.ONES, 0));
        moves.add(new GameMove("32212", 6, ScoreType.TWOS, 1));
        moves.add(new GameMove("32133", 9, ScoreType.THREES, 2));
        moves.add(new GameMove("44144", 16, ScoreType.FOURS, 3));
        moves.add(new GameMove("54122", 5, ScoreType.FIVES, 4));
        moves.add(new GameMove("56662", 18, ScoreType.SIXES, 5));

        moves.add(new GameMove("26662", 22, ScoreType.THREEOFAKIND, 6));
        moves.add(new GameMove("34444", 19, ScoreType.FOUROFAKIND, 7));
        moves.add(new GameMove("34433", 25, ScoreType.FULLHOUSE, 8));
        moves.add(new GameMove("34152", 30, ScoreType.SMALLSTRAIGHT, 9));
        moves.add(new GameMove("24356", 40, ScoreType.BIGSTRAIGHT, 10));
        moves.add(new GameMove("11111", 50, ScoreType.YATZY, 11));
        moves.add(new GameMove("14421", 12, ScoreType.CHANCE, 12));
    }

    @Override
    public GameMove getPlayerMove() {
        //GameMove(String dices, int score, ScoreType scoreType)
        try {
            Thread.sleep((long) (Math.random() * 4000) + 1000);
        } catch (InterruptedException ignored) {}
        return getRandomScore();
    }

    private GameMove getRandomScore() {
        int index = (int)(Math.random() * 12);
        GameMove move = moves.get(index);
        moves.remove(index);
        totalScore += move.getScore();
        move.setTotalScore(totalScore);
        return move;
    }
}
