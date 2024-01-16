package com.example.network;

public class Session {
    private String address;
    private int port;
    private ConnectionListener listener;
    private NetworkHandler networkHandler;

    public void createServer(String port) {
        this.port = Integer.parseInt(port);
        String response = "Waiting for player...";
        networkHandler = new NetworkHandler();
        listener = new ConnectionListener(networkHandler, this.port);
        listener.listen();
    }
}
