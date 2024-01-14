package com.example.network;

import com.example.controller.GameController;
import com.example.controller.PvPGameController;
import com.example.game.GameMove;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class NetworkHandler implements EventHandler {
    PvPGameController controller;

    public NetworkHandler(PvPGameController controller) {
        this.controller = controller;
    }
    @Override
    public void handle(Event event) {
        ConnectionHandler handler = (ConnectionHandler) event.getSource();
        Socket socket = handler.getSocket();
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            // Gamemove or any other serialisable object used with ObjectInputStream
            // must have the same package structure both in server and client
            GameMove move = (GameMove) input.readObject();
            System.out.println("Opponent moving: " + move);
            controller.OpponentMove(move);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
