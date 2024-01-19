package com.example.network;

public class Session {
    private String address;
    private int port;
    private ConnectionListener listener;
    private NetworkHandler networkHandler;

    public Session() {

    }
    public void createServer(String port) {
        this.port = Integer.parseInt(port);
        networkHandler = new NetworkHandler();
        listener = new ConnectionListener(networkHandler, this.port);
        listener.listen();
    }

    public void addNetworkhandler(NetworkHandler networkHandler) {
        this.networkHandler = networkHandler;
    }

    public NetworkHandler getNetworkHandler() {
        return networkHandler;
    }
}
