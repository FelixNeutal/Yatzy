package com.example.network;

import java.io.IOException;
import java.net.ServerSocket;

public class YatzyServer extends Thread {
    private ServerSocket serverSocket;
    private int port;

    public YatzyServer(int port) {
        this.port = port;
    }

    public void listen() {
        start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (!serverSocket.isClosed()) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
