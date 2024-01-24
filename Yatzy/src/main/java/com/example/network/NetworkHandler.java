package com.example.network;

import com.example.controller.NetworkMenuController;
import com.example.controller.PvPGameController;
import com.example.game.GameMove;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class NetworkHandler implements EventHandler {
    PvPGameController gameController;
    NetworkMenuController networkMenuController;

    public NetworkHandler() {
    }
    @Override
    public void handle(Event event) {
        ConnectionHandler handler = (ConnectionHandler) event.getSource();
        Socket socket = handler.getSocket();
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            // Gamemove or any other serialisable object used with ObjectInputStream
            // must have the same package structure both in server and client
            Packet packet = (Packet) input.readObject();
            if (packet.getHeader() == Header.OPPONENT_MOVE) {
                GameMove move = (GameMove) packet.getPayload();
                gameController.OpponentMove(move);
            } else if (packet.getHeader() == Header.NEW_CONNECTION) {
                networkMenuController.getSession().setRemoteAddress(socket.getRemoteSocketAddress().toString());
                networkMenuController.getSession().setRemotePort(socket.getPort());
                networkMenuController.startGame();
            } else if (packet.getHeader() == Header.CONNECTED) {
                networkMenuController.startGame();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setGameController(PvPGameController controller) {
        this.gameController = controller;
    }

    public void setNetworkMenuController(NetworkMenuController controller) {
        this.networkMenuController = controller;
    }
}
