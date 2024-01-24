package com.example.network;

import com.example.game.GameMove;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Session {
    private String remoteAddress;
    private int localPort;
    private int remotePort;
    private String localAddress;
    private ConnectionListener listener;
    private NetworkHandler networkHandler;

    public Session() {

    }
    public void createServer(String address, String port) {
        localPort = Integer.parseInt(port);
        localAddress = address;
        networkHandler = new NetworkHandler();
        listener = new ConnectionListener(networkHandler, localPort);
        listener.listen();
    }

    public void addNetworkhandler(NetworkHandler networkHandler) {
        this.networkHandler = networkHandler;
    }

    public NetworkHandler getNetworkHandler() {
        return networkHandler;
    }

    public void send(Packet packet) {
        String response;
        System.out.println("Sending packet");
        try (Socket socket = new Socket(remoteAddress, remotePort)) {
            //Packet packet = new Packet(Header.OPPONENT_MOVE, gameMove);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(packet);
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }

    public void handShake() {
        Packet packet = new Packet(Header.NEW_CONNECTION, null);
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }
}
