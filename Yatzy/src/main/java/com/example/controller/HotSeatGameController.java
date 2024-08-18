package com.example.controller;

import com.example.game.GameMove;
import javafx.scene.control.ToggleButton;
import java.io.IOException;

public class HotSeatGameController extends GameController {
    public HotSeatGameController() {
        player1Turn = "Player 1 turn";
        player2Turn = "Player 2 turn";
    }

    @Override
    protected void opponentMove() {
        game.switchCurrentPlayer();
    }
}
