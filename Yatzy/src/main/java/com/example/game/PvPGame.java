package com.example.game;

public class PvPGame extends Game {
    private int player2TotalScore;
    public PvPGame() {
        super();
    }

    @Override
    public GameMove onPlay(int index) {
        GameMove gameMove = new GameMove();
        gameMove.setScoreIndex(index);
        gameMove.setDices(getHand());
        gameMove.setScore(currentScores.get(index));
        player1.addToScore(currentScores.get(index));
        //player.addToUpperSectionScore(score);
        if (index < UPPERSECTIONLIMIT) {
            gameMove.setGotBonus(player1.addToBonusScore(currentScores.get(index)));
            gameMove.setUpperScore(player1.getUpperSectionScore());
        }
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
        return this.player2.getPlayerMove();
    }

    @Override
    public int getPlayer2TotalScore() {
        return player2TotalScore;
    }
}
