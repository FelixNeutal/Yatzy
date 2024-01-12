package com.example.server;


import com.example.TestJson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;

public class YatzyServer {
    private ServerSocket serverSocket;
    private HashMap<UUID, Socket> users;
    private int port;

    public static void main(String[] args) {
        YatzyServer server = new YatzyServer(8000);
        server.run();
    }
    public YatzyServer(int port) {
        users = new HashMap<>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Running server");
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}