package com.example.network;

import com.example.game.GameMove;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String address;
    private int port;
    private final String NEW_CONNECTION = "NEW_CONNECTION";
    private final String OPPONENT_MOVE = "OPPONENT_MOVE";
    private final String SUCCESS = "SUCCESS";
    private final String FAILURE = "FAILURE";

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public String send(GameMove gameMove) {
        String response;
        try (Socket socket = new Socket(address, port)) {
            Packet packet = new Packet(Header.OPPONENT_MOVE, gameMove);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(packet);
            response = SUCCESS;
        } catch (IOException e) {
//            throw new RuntimeException(e);
            response = FAILURE;
        }
        return response;
    }
}
