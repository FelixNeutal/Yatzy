package com.example.client;

import com.example.game.GameMove;

import java.util.UUID;

public class Packet {
    private UUID uuid;
    private String playerName;
    private String status;
    private GameMove gameMove;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GameMove getGameMove() {
        return gameMove;
    }

    public void setGameMove(GameMove gameMove) {
        this.gameMove = gameMove;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
