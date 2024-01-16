package com.example.network;


import javafx.event.EventHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class ConnectionListener extends Thread {
    EventHandler handler;

    public ConnectionListener(EventHandler handler, int port) {
        this.handler = handler;
    }

    public void listen() {
        start();
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(2025)) {
            while (true) {
                System.out.println("Waiting for client...");
                ConnectionHandler connectionHandler = new ConnectionHandler(this, serverSocket.accept());
                connectionHandler.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public EventHandler getHandler() {
        return handler;
    }
}
