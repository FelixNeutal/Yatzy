package com.example.network;


import javafx.event.EventHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class ConnectionListener extends Thread {
    EventHandler handler;
    private int localPort;

    public ConnectionListener(EventHandler handler, int port) {
        this.handler = handler;
        this.localPort = port;
    }

    public void listen() {
        start();
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(localPort)) {
            while (true) {
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
