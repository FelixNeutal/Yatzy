package com.example.network;

import javafx.event.ActionEvent;

import java.net.Socket;

public class ConnectionHandler extends Thread {
    private Socket socket;
    private ConnectionListener listener;

    public ConnectionHandler(ConnectionListener listener, Socket socket) {
        this.listener = listener;
        this.socket = socket;
    }

    @Override
    public void run() {
        ActionEvent event = new ActionEvent(this, null);
        listener.getHandler().handle(event);
    }

    public Socket getSocket() {
        return socket;
    }
}
